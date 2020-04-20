package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suzhou.cabinet.entity.*;
import com.suzhou.cabinet.mapper.CabinetMapper;
import com.suzhou.cabinet.utils.BaiDuMapUtil;
import com.suzhou.cabinet.utils.RestResult;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
public class CabinetService {
    @Autowired
    CabinetMapper cabinetMapper;

    @Autowired
    RegionService regionService;

    @Autowired
    BoxService boxService;

    public RestResult<String> addCabinet(Cabinet cabinet) {
        cabinet.setId(IdWorker.get32UUID());
        Date date = new Date();// 获取当前时间
        DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        cabinet.setCreateTime(date);
        cabinet.setDelFlag("0");
        cabinet.setRegionId(getRegionIdByPoint(cabinet.getLongitude(), cabinet.getLatitude()));
        cabinetMapper.insert(cabinet);
        new Thread(()->{
            boxService.insertInit(cabinet.getId());
        }).start();
        return RestResult.success(cabinet.getId());
    }

    private String getRegionIdByPoint(String longitude, String latitude) {
        double[] doubles = BaiDuMapUtil.String2Double(new String[]{longitude, latitude});
        RestResult<List<Region>> listRestResult = regionService.pointJudgement(doubles[1], doubles[0]);
        return listRestResult.getResultData().size() <= 0 ? "" : listRestResult.getResultData().get(0).getId();
    }

    public RestResult<String> deleteCabinet(String id) {
        List<Box> b=boxService.selByCabinetId(id);
        cabinetMapper.updCabinet(id);
        return RestResult.success("success");
    }

    public RestResult<List<Cabinet>> getCabinetByRegion(String id) {
        List<Cabinet> cabinets = cabinetMapper.selByRegionId(id);
        return RestResult.success(cabinets.size() <= 0 ? new ArrayList<>() : cabinets);
    }

    public RestResult<Page<CabinetVO>> listCabinet(CabinetVO vo) {
        Page<CabinetVO> page = new Page<>();
        page.setCurrent(vo.getCurrentPage() > 0 ? vo.getCurrentPage() : 1);
        page.setSize(Math.max(vo.getPageSize(), 5));
        List<Cabinet> cabinets = cabinetMapper.selCabinetByPage(page);
        Map<String, List<Region>> collect =
                regionService.listRegions().stream().collect(Collectors.groupingBy(Region::getId));
        List<CabinetVO> cabinetVOS = new ArrayList<>();
        cabinets.forEach(cabinet -> {
            CabinetVO c = new CabinetVO();
            c.setId(cabinet.getId());
            c.setName(cabinet.getName());
            c.setCreateTime(DateFormatUtils.format(cabinet.getCreateTime(), "yyyy-MM-dd"));
            List<Region> regions = collect.get(cabinet.getRegionId());
            if (ObjectUtil.isNotNull(regions) || regions.size() > 0) {
                c.setRegionId(collect.get(cabinet.getRegionId()).get(0).getName());
            }
            cabinetVOS.add(c);
        });
        page.setRecords(cabinetVOS);
        return RestResult.success(page);
    }

    public void updPointByRegionId(String id) {
        List<Cabinet> cabinets=cabinetMapper.selByRegionId(id);
        cabinets.forEach(cabinet -> {
            cabinet.setRegionId(getRegionIdByPoint(cabinet.getLongitude(), cabinet.getLatitude()));
            cabinetMapper.updateById(cabinet);
        });
    }

    public RestResult<String> updateCabinet(CabinetVO vo) {
        Cabinet cabinet=new Cabinet();
        cabinet.setId(vo.getId());
        cabinet.setName(vo.getName());
        cabinetMapper.updateById(cabinet);
        return RestResult.success("success");
    }

    public RestResult<List<Cabinet>> getMissions(String id) {
        List<Cabinet> list = cabinetMapper.selByCourierId(id);
        return RestResult.success(list);
    }
}

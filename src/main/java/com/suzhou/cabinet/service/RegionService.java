package com.suzhou.cabinet.service;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.suzhou.cabinet.entity.Region;
import com.suzhou.cabinet.entity.RegionTree;
import com.suzhou.cabinet.mapper.RegionMapper;
import com.suzhou.cabinet.utils.BaiDuMapUtil;
import com.suzhou.cabinet.utils.RestResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.suzhou.cabinet.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.suzhou.cabinet.utils.RestResult.fail;
import static com.suzhou.cabinet.utils.RestResult.success;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */

@Service
public class RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 添加区域
     */
    public RestResult<String> addRegion(Region region) {
        if (nameExists(region.getName(), region.getParentId())) {//同级目录下有同名
            return fail("insert error", "同级目录下不能有同名");
        }
        String parentId = region.getParentId();
        Region region1 = regionMapper.selectById(parentId);
        if (region1 == null) {
            region.setIsDirectory("1");
            region.setParentId("0");
        } else if ("0".equals(region1.getIsDirectory())) {//父级不是目录
            return fail("insert error", "不能在区域下新建");
        }
        Integer count = regionMapper.selSeqNoCount(region.getParentId());
        if (count == null) {
            count = 0;
        }
        int seq = count + 1;

        region.setId(IdWorker.get32UUID());
        Date date = new Date();// 获取当前时间
        DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        region.setCreateTime(date);
        region.setUpdateTime(date);
        region.setDelFlag("0");
        regionMapper.insert(region);
        return success(region.getId());
    }

    private boolean nameExists(String name, String parentId) {
        Region region = regionMapper.selByNameAndParentId(name, parentId);
        return region != null;
    }

    /**
     * 更新区域
     */
    public RestResult<Void> setRegion(Region region) {
        if(region.getBoundaryPoint()!=null&&region.getBoundaryPoint().length()!=0){
            Region region2 = regionMapper.selectById(region.getId());
            if("1".equals(region2.getIsDirectory())){
                return fail("update error","无法在目录上进行修改");
            }
        }
        if (nameUpdateExists(region.getId(), region.getName(), region.getParentId())) {//同级目录下有同名
            return fail("insert error", "同级目录下不能有同名");
        }
        Region region1 = regionMapper.selectById(region.getId());
        region.setCreateTime(region1.getCreateTime());
        Date date = new Date();// 获取当前时间
        DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        region.setUpdateTime(date);
        Integer integer = regionMapper.updateById(region);
        if (integer == 0) {
            return fail("update failure", "can not update the region");
        }
        return success(null);
    }

    private boolean nameUpdateExists(String id, String name, String parentId) {
        Region region = regionMapper.selUpdateNameAndParentId(id, name, parentId);
        return region != null;
    }


    /**
     * 递归删除区域
     */
    public RestResult<Void> deleteRegionById(String id) {
        List<String> str=new ArrayList<>();
        Map<String, List<Region>> collect = regionMapper.selAllRegions().stream().collect(Collectors.groupingBy(item -> item.getParentId()));
        getALlByParentId(id,str,collect);
        if(str.size()>0){
            regionMapper.delRegionByIds(str);
        }
        return success(null);
    }

    private void getALlByParentId(String id, List<String> str, Map<String, List<Region>> collect) {
        str.add(id);
        List<Region> regions = collect.get(id);
        if(ObjectUtil.isNotNull(regions)){
            regions.forEach(region -> {
                getALlByParentId(region.getId(),str,collect);
            });
        }
    }

    /**
     * 递归区域树
     */
    public RestResult<List<RegionTree>> getRegionTree() {
        List<RegionTree> regionTrees = regionMapper.selAllFatherPoint();//获取所有父节点
        Map<String, List<RegionTree>> collect = regionMapper.selNotFather().stream().distinct().collect(Collectors.groupingBy(item -> item.getParentId()));
        for (RegionTree regionTree : regionTrees) {//为根节点添加子区域
            getSon(regionTree, collect);
        }
        return success(regionTrees);
    }

    private void getSon(RegionTree regionTree, Map<String, List<RegionTree>> collect) {
        List<RegionTree> regionTrees = collect.get(regionTree.getId());
        if (regionTrees == null || regionTrees.size() == 0) {//没有孩子了
            regionTree.setChild(new ArrayList<>());
        } else {
            regionTree.setChild(regionTrees);
            for (RegionTree tree : regionTrees) {
                getSon(tree, collect);
            }
        }
    }

    /**
     * 根据id查区域信息
     */
    public RestResult<List<Region>> getRegionById(String id) {
        Region region = regionMapper.selectById(id);
        if ("1".equals(region.getIsDirectory())) {
            Map<String, List<Region>> collect = regionMapper.selAllRegions().stream().collect(Collectors.groupingBy(item -> item.getParentId()));
            //返回所有叶子
            List<Region> regions = new ArrayList<>();
            makeList(region.getId(), regions, collect);
            return success(regions);
        }
        return success(Collections.singletonList(region));
    }

    private void makeList(String id, List<Region> regions, Map<String, List<Region>> collect) {
        List<Region> regions1 = collect.get(id);//regionMapper.selRegionsByParentId(id);
        if (ObjectUtil.isNotNull(regions1)) {
            regions1.forEach(region -> {
                if ("1".equals(region.getIsDirectory())) {
                    makeList(region.getId(), regions, collect);
                } else {
                    regions.add(region);
                }
            });
        }
    }

    public RestResult<List<Region>> pointJudgement(Double lat, Double lng) {
        List<Region> regionList = regionMapper.selRegions();
        List<Region> rs = new ArrayList<>();
        regionList.forEach(region -> {
            String[] split = region.getBoundaryPoint().split("\\{\"lng\":");
            if (split.length > 3) {
                String[] lngs = new String[split.length - 1];
                String[] lats = new String[split.length - 1];
                for (int i = 1; i < split.length; i++) {
                    String[] s = split[i].split(",\"lat\":");
                    lngs[i - 1] = s[0];
                    lats[i - 1] = s[1].substring(0, s[1].indexOf("}"));
                }
                boolean b = BaiDuMapUtil.JudgeInOutFirst(String2Double(lngs), String2Double(lats), lng, lat);
                if (b) {
                    rs.add(region);
                }
            }
        });
        return success(rs);
    }

    public RestResult<List<Region>> pointJudgement(String placeName) {
        String str = BaiDuMapUtil.getGeocoderLatitude(placeName);
        int lngStart = str.indexOf("lng:");
        int lngEnd = str.indexOf("lat:");
        int latEnd = str.length();
        if (lngStart >= 0 && lngEnd >= 0 && latEnd > 0) {
            String lng = str.substring(lngStart + 4, lngEnd);
            String lat = str.substring(lngEnd + 4, latEnd);
            double[] d = String2Double(new String[]{lng, lat});
            return pointJudgement(d[1], d[0]);
        }
        return success(new ArrayList<>());
    }

    private static double[] String2Double(String[] List) {
        double[] GpsList = new double[List.length];
        for (int i = 0; i < List.length; i++) {
            GpsList[i] = Double.parseDouble(List[i]);
        }
        return GpsList;
    }

    public RestResult<String> pointToName(Double lat, Double lng) {
        try {
            return success(BaiDuMapUtil.getPosition(lat.toString(),lng.toString()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return fail("region search error","查询失败");
    }
}

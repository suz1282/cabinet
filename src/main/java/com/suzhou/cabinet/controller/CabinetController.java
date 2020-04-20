package com.suzhou.cabinet.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.Cabinet;
import com.suzhou.cabinet.entity.CabinetVO;
import com.suzhou.cabinet.service.CabinetService;
import com.suzhou.cabinet.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@RestController
@Api(description = "快递柜管理")
@RequestMapping("/cabinet")
@CrossOrigin("*")
public class CabinetController {
    @Autowired
    CabinetService cabinetService;

    @PostMapping("/addCabinet")
    @ApiOperation("添加快递柜")
    public RestResult<String> addCabinet(@RequestBody Cabinet cabinet){
        return cabinetService.addCabinet(cabinet);
    }

    @GetMapping("/getCabinetByRegion/{id}")
    @ApiOperation("通过区域id获取快递柜")
    public RestResult<List<Cabinet>> getCabinetByRegion(@PathVariable("id") String id){
        return cabinetService.getCabinetByRegion(id);
    }

    @GetMapping("/deleteCabinet/{id}")
    @ApiOperation("删除快递柜")
    public RestResult<String> deleteCabinet(@PathVariable("id") String id){
        return cabinetService.deleteCabinet(id);
    }

    @PostMapping("/Cabinet/list")
    @ApiOperation("快递柜消息")
    public RestResult<Page<CabinetVO>> listCabinet(@RequestBody CabinetVO vo){
        return cabinetService.listCabinet(vo);
    }

    @PostMapping("/updateCabinet")
    @ApiOperation("快递柜消息")
    public RestResult<String> updateCabinet(@RequestBody CabinetVO vo){
        return cabinetService.updateCabinet(vo);
    }

    @GetMapping("/getMissions/{id}")
    @ApiOperation("快递员任务显示在地图上")
    public RestResult<List<Cabinet>> getMissions(@PathVariable("id") String id){
        return cabinetService.getMissions(id);
    }
}


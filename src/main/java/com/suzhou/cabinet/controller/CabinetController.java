package com.suzhou.cabinet.controller;


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
    @ApiOperation("添加快递柜")
    public RestResult<List<Cabinet>> getCabinetByRegion(@PathVariable("id") String id){
        return cabinetService.getCabinetByRegion(id);
    }

    @GetMapping("/deleteCabinet/{id}")
    @ApiOperation("添加快递柜")
    public RestResult<String> deleteCabinet(@PathVariable("id") String id){
        return cabinetService.deleteCabinet(id);
    }

    @GetMapping("/Cabinet/list")
    @ApiOperation("添加快递柜")
    public RestResult<List<CabinetVO>> listCabinet(){
        return cabinetService.listCabinet();
    }
}


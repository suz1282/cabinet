package com.suzhou.cabinet.controller;


import com.suzhou.cabinet.entity.Region;
import com.suzhou.cabinet.entity.RegionTree;
import com.suzhou.cabinet.service.RegionService;
import com.suzhou.cabinet.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/region")
@Api(description = "区域管理")
@CrossOrigin("*")
public class RegionController {
    @Autowired
    RegionService regionService;

    @PostMapping("/addRegion")
    @ApiOperation("添加区域")
    public RestResult<String> addRegion(@RequestBody Region region){
        return regionService.addRegion(region);
    }

    @PostMapping("/setRegion")
    @ApiOperation("修改区域")
    public RestResult<Void> setRegion(@RequestBody Region region){
        return regionService.setRegion(region);
    }

    @GetMapping("/deleteRegion/{id}")
    @ApiOperation("删除区域")
    public RestResult<Void> deleteRegionById(@PathVariable("id") String id){
        return regionService.deleteRegionById(id);
    }

    @GetMapping("/getRegionsTree")
    @ApiOperation("区域树")
    public RestResult<List<RegionTree>> getRegionsTree(){
        return regionService.getRegionTree();
    }

    @GetMapping("/getRegion/{id}")
    @ApiOperation("通过id查区域信息")
    public RestResult<List<Region>> getRegionById(@PathVariable("id") String id){
        return regionService.getRegionById(id);
    }

    @PostMapping("/getRegions")
    @ApiOperation("所有区域")
    public RestResult<List<Region>> getRegions(){
        return regionService.getRegions();
    }

    @GetMapping("/pointJudgement")
    @ApiOperation("通过经纬度查点所在的区域")
    public RestResult<List<Region>> pointJudgement(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng){
        return regionService.pointJudgement(lat,lng);
    }

    @GetMapping("/placeNameJudgement/{placeName}")
    @ApiOperation("通过地名查所在的区域")
    public RestResult<List<Region>> pointJudgement(@PathVariable("placeName") String placeName){
        return regionService.pointJudgement(placeName);
    }

    @GetMapping("/pointToName")
    @ApiOperation("坐标点查名字")
    public RestResult<String> pointToName(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng){
        return regionService.pointToName(lat,lng);
    }

}


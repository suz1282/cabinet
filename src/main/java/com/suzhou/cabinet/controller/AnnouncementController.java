package com.suzhou.cabinet.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.AnnounceSearchPage;
import com.suzhou.cabinet.entity.Announcement;
import com.suzhou.cabinet.service.AnnouncementService;
import com.suzhou.cabinet.utils.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/announcement")
@Api(description = "公告内容增删改查管理")
@CrossOrigin("*")
public class AnnouncementController {
    @Autowired
    AnnouncementService announcementService;

    @PostMapping("/addAnnouncement")
    @ApiOperation("公告添加")
    public RestResult<Void> addAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);
    }

    @PostMapping("/deleteAnnouncement")
    @ApiOperation("公告删除")
    public RestResult<Page<Announcement>> deleteAnnouncement(@RequestBody AnnounceSearchPage announceSearchPage) {
        return announcementService.deleteAnnouncement(announceSearchPage);
    }

    @PostMapping("/updateAnnouncement")
    @ApiOperation("公告更新")
    public RestResult<Void> updateAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.updateAnnouncement(announcement);
    }

    @GetMapping("/getAnnouncement/{id}")
    @ApiOperation("显示公告详细")
    public RestResult<Announcement> getReadAnnouncement(@PathVariable("id") String id) {
        return announcementService.getMsg(id);
    }

    //模糊查询
    @PostMapping("/getAnnouncement")
    @ApiOperation("查询公告")
    public RestResult<Page<Announcement>> getAnnouncement(@RequestBody AnnounceSearchPage announceSearchPage) {
        return announcementService.getAnnounceSearchPage(announceSearchPage);
    }
}


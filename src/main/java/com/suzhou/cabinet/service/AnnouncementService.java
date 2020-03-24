package com.suzhou.cabinet.service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suzhou.cabinet.entity.AnnounceSearchPage;
import com.suzhou.cabinet.utils.RestResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suzhou.cabinet.entity.Announcement;
import com.suzhou.cabinet.mapper.AnnouncementMapper;
import com.suzhou.cabinet.utils.RestResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.suzhou.cabinet.utils.RestResult.fail;
import static com.suzhou.cabinet.utils.RestResult.success;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Service
@Transactional
public class AnnouncementService {

    @Autowired
    AnnouncementMapper announcementMapper;

    /**
     * 添加公告announcement
     */
    //添加事务
    public RestResult<Void> addAnnouncement(Announcement announcement) {
        if (announcement == null) return fail("insert error", "不能提交空公告");
        if (StringUtils.isEmpty(announcement.getContent())) return fail("insert error", "内容不能为空");
        if (StringUtils.isEmpty(announcement.getTitle())) return fail("insert error", "标题不能为空");
        if (StringUtils.isEmpty(announcement.getStartTime().toString())) return fail("insert error", "开始时间不能为空");
        if (StringUtils.isEmpty(announcement.getEndTime().toString())) return fail("insert error", "结束时间不能为空");
        if (StringUtils.isEmpty(announcement.getType())) return fail("insert error", "公告类型不能为空");

        announcement.setId(IdWorker.get32UUID());
        announcement.setDelFlag("0");
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        announcement.setCreateTime(date);
        announcement.setUpdateTime(date);

        //公告添加
        Integer insert = announcementMapper.insert(announcement);
        if (insert == 0) {
            return fail("insert error", "添加公告失败!");
        }

        //添加到公告已读announcement_read_rel

        return success(null);
    }

    /**
     * 公告删除
     */
    public RestResult<Page<Announcement>> deleteAnnouncement(AnnounceSearchPage announceSearchPage) {
        Announcement announcement = announcementMapper.selAnnouncementById(announceSearchPage.getId());
        if (announcement == null) {
            return fail("delete error", "公告不存在");
        }
        Date date = new Date();// 获取当前时间
        DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        if (betweenStartAndEnd(announcement, date)) {
            return fail("delete error", "公告开始后不能删除");
        }
        return getAnnounceSearchPage(announceSearchPage);
    }

    //公告是否在执行时间内
    private boolean betweenStartAndEnd(Announcement announcement, Date date) {
        Date startTime = announcement.getStartTime();
        Date endTime = announcement.getEndTime();
        return date.compareTo(startTime) > 0 && date.compareTo(endTime) < 0;
    }

    /**
     * 公告修改
     */
    public RestResult<Void> updateAnnouncement(Announcement announcement) {
        if (announcementIsEmpty(announcement)) return fail("insert error", "不能提交为空");

        Announcement announcement1 = announcementMapper.selAnnouncementById(announcement.getId());
        Date date = new Date();// 获取当前时间
        DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        int i = date.compareTo(announcement1.getStartTime());
        if (i < 0) {//date<start
            announcement.setUpdateTime(date);
            announcement.setCreateTime(announcement1.getCreateTime());
            announcementMapper.updateById(announcement);
            //公告已读信息更新
            return success(null);
        }
        return fail("update failure", "开始后无法修改");
    }

    private boolean announcementIsEmpty(Announcement announcement) {
        if (announcement == null) return true;
        if (StringUtils.isEmpty(announcement.getContent())) return true;
        if (StringUtils.isEmpty(announcement.getTitle())) return true;
        if (StringUtils.isEmpty(announcement.getStartTime().toString())) return true;
        if (StringUtils.isEmpty(announcement.getEndTime().toString())) return true;
        if (StringUtils.isEmpty(announcement.getType())) return true;
        return false;
    }

    /*
     * 显示公告详情
     *
     */
    public RestResult<Announcement> getMsg(String announcementId) {
        Announcement announcement = announcementMapper.selAnnouncementById(announcementId);
        //id转姓名
        return success(announcement);
    }

    //显示公告分页
    public RestResult<Page<Announcement>> getAnnounceSearchPage(AnnounceSearchPage announceSearchPage) {
        Page<Announcement> page = new Page<>();
        page.setSize(announceSearchPage.getSize() < 1 ? 10 : announceSearchPage.getSize());
        page.setCurrent(Math.max(announceSearchPage.getCurrent(), 1));
        List<Announcement> announcements = announcementMapper.selAnnouncePageList(page, announceSearchPage);
        //id转名字
       page.setRecords(announcements);
        return success(page);
    }

}

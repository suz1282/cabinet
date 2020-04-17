package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Author : SUZ
 * @Date : 2020/4/17
 * @Description :
 */
@Data
public class AnnouncementVO {
    private String id;

    private String title;

    private String type;

    private String content;

    private String startTime;

    private String endTime;

    private String createTime;

    private String updateTime;

    private String delFlag;

    @TableField("picture_name")
    private String pictureName;
}

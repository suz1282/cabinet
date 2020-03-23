package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Data
public class Announcement extends Model<Announcement> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String type;

    private String content;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delFlag;

    @TableField("picture_name")
    private String pictureName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

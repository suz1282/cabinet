package com.suzhou.cabinet.entity;

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
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String parentId;

    private String boundaryPoint;

    private String delFlag;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String isDirectory;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

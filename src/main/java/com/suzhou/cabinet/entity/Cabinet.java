package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Data
public class Cabinet extends Model<Cabinet> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Date createTime;

    private String regionId;

    private String longitude;

    private String latitude;

    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

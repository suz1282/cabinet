package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

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
public class Box extends Model<Box> {

    private static final long serialVersionUID = 1L;

    /**
     * 高度
     */
    private Double h;

    /**
     * 宽度
     */
    private Double w;

    /**
     * 深度
     */
    private Double d;

    private String id;

    private String name;

    private String isEmpty;

    private String cabinetId;

    private String delFlag;

    private String boxType;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

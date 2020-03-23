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
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String boxId;

    private Date arrivalTime;

    private String courierId;

    private String userId;

    private String code;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}

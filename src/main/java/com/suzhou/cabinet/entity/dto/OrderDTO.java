package com.suzhou.cabinet.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author : SUZ
 * @Date : 2020/4/14
 * @Description :
 */
@Data
public class OrderDTO {
    private Double h;

    private Double w;

    private Double d;

    private String courierId;
    private String courierName;
    private String courierPassword;

    private String cabinetId;

    private String orderCode;

    private String createTime;
}

package com.suzhou.cabinet.entity;

import lombok.Data;

/**
 * @Author : SUZ
 * @Date : 2020/4/10
 * @Description : data transfor object
 */
@Data
public class CabinetDTO {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String createTime;

    private String regionId;

    private String longitude;

    private String latitude;
}

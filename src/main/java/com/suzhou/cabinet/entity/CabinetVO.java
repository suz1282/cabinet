package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author : SUZ
 * @Date : 2020/4/10
 * @Description : 快递柜view_object
 */
@Data
public class CabinetVO {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String createTime;

    private String regionId;

    private String longitude;

    private String latitude;

}

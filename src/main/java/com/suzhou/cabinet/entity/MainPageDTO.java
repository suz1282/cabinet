package com.suzhou.cabinet.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author : SUZ
 * @Date : 2020/4/20
 * @Description : 主页表
 */
@Data
public class MainPageDTO {
    private List<String> regionIds;

    private String startTime;

    private String endTime;

    private Integer step;

}

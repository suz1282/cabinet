package com.suzhou.cabinet.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author : SUZ
 * @Date : 2020/4/21
 * @Description :
 */
@Data
public class MainPageVO {
    private String id;
    private String name;
    private List<Integer> data;
}

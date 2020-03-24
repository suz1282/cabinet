package com.suzhou.cabinet.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author : SUZ
 * @Date : 2020/01/13 09:04
 * @Description :
 */
@Data
public class AnnounceSearchPage implements Serializable {
    private String id;
    private int current;
    private int size;
    private String title;
    private String type;
}

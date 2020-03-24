package com.suzhou.cabinet.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author : SUZ
 * @Date : 2020/01/10 16:07
 * @Description : 分页
 */
@Data
public class AnnouncePage<P> {
    private Integer currentPage;
    private Integer totalPage;
    private Integer pageSize;
    private List<P> content;
}

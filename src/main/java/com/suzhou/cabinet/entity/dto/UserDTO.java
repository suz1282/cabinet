package com.suzhou.cabinet.entity.dto;

import lombok.Data;

/**
 * @Author : SUZ
 * @Date : 2020/4/26
 * @Description : user data transform object
 */
@Data
public class UserDTO {
    private Integer currentPage;
    private Integer pageSize;
    private String id;
    private String name;
    private String password;
    private String type;
    private String cabinetId;
}

package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : SUZ
 * @Date : 2020/4/14
 * @Description : 快递员及任务数
 */
@Data
public class UserVO extends Model<User> {
    private String id;

    @ApiModelProperty(value = "用户名")
    private String name;

    private Integer orderNumber;
}

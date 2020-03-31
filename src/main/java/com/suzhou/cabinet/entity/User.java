package com.suzhou.cabinet.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author suz
 * @since 2020-03-23
 */
@Data
public class User extends Model<User> {

    //private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 1:用户  2:快递员 3:管理员
     */

    private String type;


    private String delFlag;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

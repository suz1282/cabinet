package com.suzhou.cabinet.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;

/**
 * @Author : SUZ
 * @Date : 2020/01/08 11:14
 * @Description :
 */
@Data
public class RegionTree {

    @ApiModelProperty(value = "主键",required = false)
    private String id;

    @ApiModelProperty(value = "名字",required = false)
    private String name;

    @ApiModelProperty(value = "父级区域id。默认一个0的父级区域id",required = true,example = "0")
    @TableField("parent_id")
    private String parentId;

    @TableField("is_directory")
    private String isDirectory;

    @ApiModelProperty(value = "子树",required = false)
    private List<RegionTree> child;
}

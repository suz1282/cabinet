package com.suzhou.cabinet.enums;

/**
 * @Author : SUZ
 * @Date : 2020/4/13
 * @Description : 箱子尺寸
 */
public enum BoxSize {
    BIG_BOX_DEPTH("big_box_depth",50.00),
    BIG_BOX_HEIGHT("big_box_height",30.00),
    BIG_BOX_WIDTH("big_box_width",30.00),
    BIG_BOX_NUMBER("big_box_number",10.00),
    MIDDLE_BOX_DEPTH("middle_box_depth",50.00),
    MIDDLE_BOX_HEIGHT("middle_box_height",20.00),
    MIDDLE_BOX_WIDTH("middle_box_width",30.00),
    MIDDLE_BOX_NUMBER("middle_box_number",10.00),
    SMALL_BOX_DEPTH("small_box_depth",50.00),
    SMALL_BOX_HEIGHT("small_box_height",10.00),
    SMALL_BOX_WIDTH("small_box_width",30.00),
    SMALL_BOX_NUMBER("small_box_number",10.00),
    ;

    private final String code;

    private final Double message;

    public String getCode() {
        return code;
    }

    public Double getMessage() {
        return message;
    }

    BoxSize(String code, Double message) {
        this.code = code;
        this.message = message;
    }
}

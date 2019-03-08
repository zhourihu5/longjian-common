package com.longfor.longjian.common.consts;

/**
 * 公共枚舉纍
 */
public enum YesNoEnum {

    YES("可用",1),
    NO("禁用",2);

    private String name;
    private Integer value;

    YesNoEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}

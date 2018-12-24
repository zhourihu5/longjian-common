package com.longfor.longjian.common.consts;

import org.apache.commons.lang3.StringUtils;

public enum DataTypeEnum {
    BOOL(1, "bool"), INT(2, "int"), DOUBLE(14, "float");

    private Integer value;
    private String title;

    DataTypeEnum(Integer value, String title) {
        this.value = value;
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    public static Integer getVal(String title) {
        for (DataTypeEnum typeEnum : DataTypeEnum.values()) {
            if (typeEnum.getTitle().equals(StringUtils.lowerCase(title))) {
                return typeEnum.getValue();
            }
        }
        return null;
    }

}

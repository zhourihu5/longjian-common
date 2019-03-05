package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Wang on 2019/3/5.
 */
public enum HouseQmCheckTaskAdminerType {

    YES    (10, "是管理员"),
    NO   (20, "不是管理员");

    @Getter
    @Setter
    private Integer key;

    @Getter @Setter
    private String value;

    HouseQmCheckTaskAdminerType(Integer key, String value) {
        this.value = value;
        this.key = key;
    }
}

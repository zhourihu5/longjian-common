package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Wang on 2019/3/1.
 */
public enum SourceType {

    TODO_NOTICE    (10, "待办通知"),
    STRATEGY_NOTIVE    (20, "预警通知");

    @Getter
    @Setter
    private Integer key;

    @Getter @Setter
    private String value;

    SourceType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (SourceType ele : values()) {
            if(ele.getKey().equals(key)) return ele.getValue();
        }
        return null;
    }

}

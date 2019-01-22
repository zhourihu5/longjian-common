package com.longfor.longjian.common.time;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lipeishuai
 * @date 2019/1/21 13:41
 */

public enum TimeType {

    DAY("day","天"),
    WEEK("week","天"),
    MONTH("month","天"),
    QUARTER("quarter","天"),
    YEAR("year","天");

    private String value;
    private String name;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    TimeType(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public static final List<String> typeList = Lists.newArrayList();

    static {
        for (TimeType type : TimeType.values()) {
            typeList.add(type.getValue());
        }
    }

}


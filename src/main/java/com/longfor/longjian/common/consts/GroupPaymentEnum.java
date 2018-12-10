package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lipeishuai on 2018/11/11.
 */
public enum GroupPaymentEnum {

    TRIAL(10, "未付费"),PURCHASED(20, "已付费");

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String value;

    GroupPaymentEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }



}

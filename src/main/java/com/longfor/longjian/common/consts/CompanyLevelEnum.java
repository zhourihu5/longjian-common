package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lipeishuai on 2018/11/11.
 */
public enum CompanyLevelEnum {

    Group(1, "集团"),Team(2, "公司"),SubTeam(3, "子公司");

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String value;

    CompanyLevelEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }



}

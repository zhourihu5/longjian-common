package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lipeishuai on 2018/11/11.
 */
public enum ZhongliangCompanyLevelEnum {

    HoldingsGroup(10, "控股集团"), RegionalGroup(20, "区域集团"),RegionalTeam(30, "区域公司"),Division(40, "事业部");

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private Integer id;

    ZhongliangCompanyLevelEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}

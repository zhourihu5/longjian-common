package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:49
 */

public enum HouseQmCheckTaskRoleType {

    Checker    (10, "检查人"),
    Repairer   (20, "整改人"),
    MixChecker (40, "多职能人员");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    HouseQmCheckTaskRoleType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

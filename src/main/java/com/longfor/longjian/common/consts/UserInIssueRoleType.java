package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:38
 */

public enum UserInIssueRoleType {
    Checker (10, "检查人"),Repairer(20, "整改负责人"), RepairerFollower(30, "整改跟进人");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    UserInIssueRoleType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

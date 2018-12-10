package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:41
 */

public enum CheckTaskRoleCanDirectApproveType {
    Yes (10, "可以直接消项"),
    No  (20, "不可以直接消项");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRoleCanDirectApproveType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

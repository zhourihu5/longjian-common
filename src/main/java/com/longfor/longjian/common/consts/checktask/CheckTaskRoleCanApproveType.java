package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:40
 */

public enum CheckTaskRoleCanApproveType {

    Yes (10, "可以消项"),
    No  (20, "不可以消项");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRoleCanApproveType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

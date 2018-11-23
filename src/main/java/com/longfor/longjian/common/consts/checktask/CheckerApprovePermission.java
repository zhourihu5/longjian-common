package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:43
 */

public enum CheckerApprovePermission {
    Yes (10, "可以销项自己整改的问题"),
     No  (20, "不可以销项自己整改的问题");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckerApprovePermission(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:41
 */

public enum CheckTaskRoleCanReassignType {
    Yes (10, "可以修改"),
    No  (20, "不可以修改");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRoleCanReassignType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}


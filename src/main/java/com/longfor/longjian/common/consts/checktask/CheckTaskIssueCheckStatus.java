package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:50
 */

public enum CheckTaskIssueCheckStatus {
    CheckYes (1, "通过"),
    CheckNo         (2, "不通过");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueCheckStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

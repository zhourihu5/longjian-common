package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:44
 */

public enum CheckTaskIssueCondition {
    Slight    (1, "轻微"),
    RangePoor (2, "较差"),
    Serious   (3, "严重");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueCondition(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 14:48
 */

public enum CheckTaskIssueType {

    Record      (99, "普通记录"),
    FindProblem (1, "发现问题"),
    Good        (2, "优秀做法"),
    Difficult   (3, "困难问题");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

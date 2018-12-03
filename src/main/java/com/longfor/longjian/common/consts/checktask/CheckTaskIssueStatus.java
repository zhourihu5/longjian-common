package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:37
 */

public enum CheckTaskIssueStatus {

    NoProblem      (10, "无问题"),
    NoteNoAssign   (20, "待分配"),
    AssignNoReform (30, "待整改"),
    ReformNoCheck  (50, "待销项"),
    CheckYes       (60, "已销项"),
    Cancel         (70, "已取消");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

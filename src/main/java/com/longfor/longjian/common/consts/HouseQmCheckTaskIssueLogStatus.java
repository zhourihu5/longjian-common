package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:48
 */

public enum HouseQmCheckTaskIssueLogStatus {

    NoProblem           (10,  "无问题"),
    NoteNoAssign        (20, "已记录未分配"),
    AssignNoReform      (30, "已分配未整改"),
    Repairing           (40, "整改中"),
    ReformNoCheck       (50, "已整改未验收"),
    CheckYes            (60, "已验收"),
    Cancel              (70, "已取消"),
    EditBaseInfo        (990, "修改基础信息"),
    UpdateIssueInfo     (991, "后台更新基础信息");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    HouseQmCheckTaskIssueLogStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

package com.longfor.longjian.common.consts;


public enum MeasureListIssueStatusEnum {
    NoteNoAssign(1,"已记录未分配"),AssignNoReform(2,"已分配未整改"),ReformNoCheck(3,"已整改未验收"),CheckYes(4,"已验收"),Closed(5,"已关闭");

    MeasureListIssueStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    private Integer value;

    private String label;

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}

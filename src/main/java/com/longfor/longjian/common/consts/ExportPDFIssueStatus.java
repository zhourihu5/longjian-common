package com.longfor.longjian.common.consts;

public enum ExportPDFIssueStatus {
    NoProblem(10, "记录"), NoteNoAssign(20, "待分配"), AssignNoReform(30, "待整改"),
    ReformNoCheck(50, "待销项"), CheckYes(60, "已销项");

    private String name;
    private Integer value;

    ExportPDFIssueStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}

package com.longfor.longjian.common.consts;

public enum EventQueueEnum {
    PKG_MEASURE_RESULT_CREATED("event_pkgs-measure-result:created", "实测测量结果已创建"),
    PKG_MEASURE_ISSUE_REPORTED("event_pkgs-measure-issue:reported", "实测问题已上报"),
    PKG_HOUSEQM_ISSUE_REPORTED("event_pkgs-houseqm-issue:reported", "工程管理问题已上报"),
    PKG_KEY_PROCEDURE_ISSUE_REPORTED("event_pkgs-key_procedure-issue:reported", "关键工序问题已上报"),
    PKG_KEY_PROCEDURE_TASK_REPORTED("event_pkgs-key_procedure-task:reported", "关键工序任务已上报"),
    BGTASK_SIMPLE_CATEGORY_UPDATE("bgtask_simple-category-update", "检查项更新"),
    API_REQ_LOG("bgtask_simple-api-req_log", "API请求记录"),
    ;

    private String name;
    private String value;

    EventQueueEnum(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

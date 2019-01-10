package com.longfor.longjian.common.consts;

/**
 * 事件类型
 */
public enum EventEnum {
    MEASURE_RESULT_CREATED("measure-result:created","测量结果已创建"),
    MEASURE_ISSUE_CREATED("measure-issue:created", "实测问题已创建"),
    MEASURE_ISSUE_ASSIGNED("measure-issue:assigned", "实测问题已指派"),
    MEASURE_ISSUE_REFORMED("measure-issue:reformed", "实测问题已整改"),
    MEASURE_ISSUE_CHECKED("measure-issue:checked", "实测问题已销项"),

    KEY_PROCEDURE_ISSUE_CREATED("key_procedure-issue:created", "工序问题已创建"),
    KEY_PROCEDURE_ISSUE_ASSIGNED("key_procedure-issue:assigned", "工序问题已指派"),
    KEY_PROCEDURE_ISSUE_REFORMED("key_procedure-issue:reformed", "工序问题已整改"),
    KEY_PROCEDURE_ISSUE_CHECKED("key_procedure-issue:checked", "工序问题已销项"),

    KEY_PROCEDURE_TASK_REPORTED("key_procedure-task:reported", "工序任务已报验"),
    KEY_PROCEDURE_TASK_CHECKED("key_procedure-task:checked", "工序任务已验收"),

    HOUSEQM_ISSUE_CREATED("houseqm-issue:created", "工程问题已创建"),
    HOUSEQM_ISSUE_ASSIGNED("houseqm-issue:assigned", "工程问题已指派"),
    HOUSEQM_ISSUE_REFORMED("houseqm-issue:reformed", "工程问题已整改"),
    HOUSEQM_ISSUE_CHECKED("houseqm-issue:checked", "工程问题已销项");
    private String name;
    private String value;

    EventEnum(String value, String name) {
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

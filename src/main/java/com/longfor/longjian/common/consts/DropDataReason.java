package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:46
 */

public enum DropDataReason {

    NotFound                  (1,   "未找到资源"),
    Exists                    (2,   "资源已存在"),
    HouseQmIssueLogUuidExists (101, "日志信息已存在"),
    HouseQmTaskRemoved        (102, "工程任务已删除"),
    Other                     (999, "未知错误");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    DropDataReason(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

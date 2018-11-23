package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:56
 */
public enum NoticeType {

    WORK_REMINDER (1, "工作提醒"), SYSTEM_NOTIFICATION(2, "系统通知");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    NoticeType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

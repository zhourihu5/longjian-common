package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 14:50
 */

public enum CheckTaskStatus {

    UnFinish (1, "未完成"),
    Finish   (2, "已完成"),
    Cancel   (3, "已取消");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

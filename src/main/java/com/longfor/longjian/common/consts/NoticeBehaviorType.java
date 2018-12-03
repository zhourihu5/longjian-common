package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:51
 */

public enum NoticeBehaviorType {
    _None   (0, "无"),
    CONSULT (1, "协商模式"),
    JUMP    (2, "跳转模式");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    NoticeBehaviorType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

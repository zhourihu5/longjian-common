package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:48
 */

public enum CheckTaskIssueAttachmentStatus {

    Enable (1, "可用"),
    Deleted (2, "已删除");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueAttachmentStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

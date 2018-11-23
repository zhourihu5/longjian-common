package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:47
 */

public enum CheckTaskIssueAttachmentPublicType {

    Public  (10,  "公有"),
    Private (20,  "私有");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueAttachmentPublicType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

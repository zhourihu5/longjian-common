package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:47
 */

public enum CheckTaskIssueAttachmentAttachmentType {

    Audio(10,  "音频");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskIssueAttachmentAttachmentType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }


}

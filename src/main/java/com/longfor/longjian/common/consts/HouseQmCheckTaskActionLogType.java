package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:49
 */

public enum HouseQmCheckTaskActionLogType {

    Create          (1, "创建问题"),
    Assign          (2, "分派问题"),
    Repair          (3, "整改问题"),
    Approve         (4, "销项"),
    Unapprove       (5, "整改审核不通过"),
    Close           (6, "关闭问题"),
    Open            (7, "打开问题"),
    ChangeType      (8, "修改问题类型"),
    AddDesc         (9, "补充描述文字"),
    AddAttachment   (10, "新增描述图片");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    HouseQmCheckTaskActionLogType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

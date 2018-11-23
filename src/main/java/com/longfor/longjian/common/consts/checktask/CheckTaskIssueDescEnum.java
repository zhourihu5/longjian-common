package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 14:43
 */

public enum CheckTaskIssueDescEnum {

    Arbitrary   (10, "文字描述和缺陷图片任意必填一项"),
    Description (20, "必填文字描述"),
    Picture     (30, "必填缺陷图片"),
    AllNeed     (40, "文字描述和缺陷图片都必填"),
    AllNotNeed  (50, "文字描述和缺陷图片都不填");


    @Getter
    @Setter
    private Integer value;

    @Getter
    @Setter
    private String label;

    CheckTaskIssueDescEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

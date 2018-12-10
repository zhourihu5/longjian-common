package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:51
 */

public enum NoticeReadStatusType {

    ALL       (0, "全部"),
    UN_READ   (1, "未读"),
    HAVE_READ (2, "已读");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    NoticeReadStatusType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

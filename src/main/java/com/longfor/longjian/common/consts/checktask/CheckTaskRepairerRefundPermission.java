package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:42
 */

public enum CheckTaskRepairerRefundPermission {

    Yes (10, "整改负责人可以退单"),
    No (20, "整改负责人不可以退单");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRepairerRefundPermission(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

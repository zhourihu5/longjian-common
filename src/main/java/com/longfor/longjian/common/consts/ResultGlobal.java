package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:50
 */

public enum ResultGlobal {

    RES_SUCCESS (0, "成功"),
    RES_FAILED  (-1, "失败"),
    RES_ERROR   (-99, "接口调用失败");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    ResultGlobal(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

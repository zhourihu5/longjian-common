package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:42
 */

public enum CheckTaskRepairerFollowerPermission {

    SubmitDescribe (10, "只能提交整改描述，不能完成整改"),
    CompleteRepair (20, "既能提交整改描述，又能完整整改");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRepairerFollowerPermission(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}

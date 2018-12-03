package com.longfor.longjian.common.consts.checktask;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:43
 */

public enum CheckTaskRepairedPictureEnum {
    UnForcePicture (10, "不强制附加照片"),
    ForcePicture   (20, "必须附加整改照片");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    CheckTaskRepairedPictureEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

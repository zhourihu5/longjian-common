package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:35
 */

public enum ModuleNameEnum {

    GCGL    (1001, "过程管理"),
    FHYS    (1007, "移动验房-分户验收"),
    RHYF    (1008, "移动验房-入伙验房"),
    CJCY    (1009, "移动验房-承接查验"),
    GDKF    (1010, "移动验房-工地开放");


    @Getter
    @Setter
    private Integer key;

    @Getter @Setter
    private String value;

    ModuleNameEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValue(Integer key) {
        for (ModuleNameEnum ele : values()) {
            if(ele.getKey().equals(key)) return ele.getValue();
        }
        return null;
    }
}


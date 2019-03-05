package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:35
 */

public enum ModuleInfoEnum {

    GCGL    (1001, "gcgl"),
    YDYF    (1002, "ydyf"),
    GXGL    (1003, "gxgl"),
    SCSL    (1004, "scsl"),
    XUNJIAN (1005, "xunjian"),
    PLAN    (1006, "plan");
    //FHYS    (1007, "fhys"),
    //RHYF    (1008, "rhyf"),
    //CJCY    (1009, "cjcy"),
    //GDKF    (1010, "gdkf");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    ModuleInfoEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getLabel(Integer value) {
        for (ModuleInfoEnum ele : values()) {
            if(ele.getValue().equals(value)) return ele.getLabel();
        }
        return null;
    }
}


package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:36
 */

public enum CategoryClsType {

    Default      (0, "V2默认检查项"),
    KeyProcedure (1, "关键工序"),
    Measure      (102, "实测实量"),
    HouseQm      (201, "移动验房"),
    BuildingQm   (301, "工程管理"),
    FBFX         (21, "分部分项"),
    YB           (22, "样板点评"),
    RCJC         (23, "日常检查"),
    YDJC         (24, "月度检查"),
    JDJC         (25, "季度检查"),
    RHYF         (26, "入伙验房"),
    ZXJC         (27, "专项检查"),
    FHYS         (28, "分户验收"),
    AQJC         (29, "安全检查"),
    CJCY         (30, "承接查验"),
    GDKF         (31, "工地开放");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    private CategoryClsType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

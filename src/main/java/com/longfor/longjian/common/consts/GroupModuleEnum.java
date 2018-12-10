package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lipeishuai on 2018/11/11.
 */
public enum GroupModuleEnum {

    GCGL("gcgl", "过程管理"),
    YDYF("ydyf", "移动验房"),
    GXGL("gxgl", "工序管理"),
    SCSL("scsl", "实测实量"),
    XUNJIAN("xunjian", "巡检"),
    PLAN("plan", "进度管理"),
    DOCS("docs", "文档协同"),
    YDKF("ydkf", "移动客服");

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String value;

    GroupModuleEnum(String id, String value) {
        this.id = id;
        this.value = value;
    }


}
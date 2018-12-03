package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:50
 */

public enum CommonGlobal {

    EXPORT_BASE_DIR ("/data/zhijian/writable", "问题导出文件绝对路径"),
    EXPORT_BASE_URI ("writable", "问题导出文件URI");


    @Getter
    @Setter
    private String value;

    @Getter @Setter
    private String label;

    CommonGlobal(String value, String label) {
        this.value = value;
        this.label = label;
    }

}

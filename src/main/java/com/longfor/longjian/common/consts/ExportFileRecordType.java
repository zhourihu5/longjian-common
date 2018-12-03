package com.longfor.longjian.common.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author lipeishuai
 * @date 2018/11/23 13:45
 */

public enum ExportFileRecordType {


    HouseQmIssuePPT     (1, "导出问题列表PPT"),
    HouseQmIssueExcel   (2, "导出问题列表Excel"),
    HouseQmIssueExcelV3 (5, "导出问题列表Excel(V3)");


    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;

    ExportFileRecordType(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}

package com.longfor.longjian.common.consts;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.Setter;

public enum TransferOrderIssueStatusEnum {
    RECORDED (1, "未分配"),ASSIGNED(2, "未整改"), REPAIRED(3, "未审核");

    @Getter
    @Setter
    private Integer value;

    @Getter @Setter
    private String label;
    
    TransferOrderIssueStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
    
    private static final Map<Integer, TransferOrderIssueStatusEnum> statusMap = Maps.newHashMap();

    static {
        for (TransferOrderIssueStatusEnum status : TransferOrderIssueStatusEnum.values()) {
            statusMap.put(status.getValue(), status);
        }
    }
    
    public static String getLabel(Integer value) {
        TransferOrderIssueStatusEnum status=statusMap.get(value);
        if(status==null) {
            return "";
        }
        return status.getLabel();
        
    }
}

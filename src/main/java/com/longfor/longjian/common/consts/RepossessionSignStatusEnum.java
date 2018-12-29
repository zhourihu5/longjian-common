package com.longfor.longjian.common.consts;

public enum RepossessionSignStatusEnum {
    Unsigned(0,"未签名"),Signed(1,"已签"),Reject(2,"拒签");

    RepossessionSignStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    private Integer value;
    private String label;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

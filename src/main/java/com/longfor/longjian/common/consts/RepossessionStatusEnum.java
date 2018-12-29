package com.longfor.longjian.common.consts;

public enum RepossessionStatusEnum {
    None(0,"未检查"),OnlyCheck(1,"业主只看房／已查验"),Accept(2,"业主收楼"),RejectAccept(3,"业主拒绝收楼");
    private Integer value;
    private String label;

    RepossessionStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

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

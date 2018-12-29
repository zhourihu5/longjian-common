package com.longfor.longjian.common.consts;

public enum RepossessionLogTypeEnum {
    HasTakeKey(1,"接收钥匙"),AccompanySign(2,"陪验人员签名"),Sign(3,"业主签字确认"),Repair(4,"翻修确认"),Remark(5,"备注"),MeterData(6,"三表数据"),RepossessionStatus(7,"业主收楼状态变更");

    private Integer value;
    private String label;

    RepossessionLogTypeEnum(Integer value, String label) {
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

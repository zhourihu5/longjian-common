package com.longfor.longjian.common.consts;

public enum RepossessionMeterTypeEnum {
    Water(1,"水表"),Electricity(2,"电表"),Gas(3,"燃气表");

    private Integer value;

    private String label;

    RepossessionMeterTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}

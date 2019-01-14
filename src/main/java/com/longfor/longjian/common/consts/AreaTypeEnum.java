package com.longfor.longjian.common.consts;
/**
 * Created by jiazhongmin on 2019/01/14.
 */
public enum AreaTypeEnum {
    PUBLIC(1, "公共区域"),
    BUILDING(2, "楼栋"),
    FLOOR(3, "层"),
    HOUSE(4, "户"),
    ROOM(5, "房间"),
    FLOOR_PUBLIC(6, "楼层公区"),
    VILLA(7, "别墅"),
    OTHER(99, "其他");

    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    AreaTypeEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}

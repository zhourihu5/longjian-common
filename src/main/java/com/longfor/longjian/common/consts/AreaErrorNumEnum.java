package com.longfor.longjian.common.consts;

/**
 * 实测错误枚举类
 *
 * @author zkm
 * @date 2019/1/9 15:40
 */
public enum AreaErrorNumEnum {

    /**
     * 楼栋类型错误
     */
    BuildingTypeError("TypeError", 1200100, "楼栋类型错误");

    private String name;
    private Integer code;
    private String message;

    AreaErrorNumEnum(String name, Integer code, String message) {
        this.name = name;
        this.code = code;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

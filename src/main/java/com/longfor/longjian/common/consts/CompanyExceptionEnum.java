package com.longfor.longjian.common.consts;

/**
 * @author zkm
 * @date 2019/1/1 22:30
 */
public enum CompanyExceptionEnum {

    /**
     * 公司不存在
     */
    NotFound(100001, "公司不存在"),
    /**
     * 公司创建失败
     */
    CreateFail(100002, "公司创建失败"),
    /**
     * 公司删除失败
     */
    DeleteFail(100003, "公司删除失败"),
    /**
     * 公司更新失败
     */
    UpdateFail(100004, "公司更新失败");

    private Integer code;
    private String message;

    CompanyExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

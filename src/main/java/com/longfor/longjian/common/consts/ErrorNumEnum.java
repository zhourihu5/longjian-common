package com.longfor.longjian.common.consts;

/**
 * 错误代码枚举类
 *
 * @author zkm
 * @date 2018/12/30 23:00
 */
public enum ErrorNumEnum {

    /**
     * 未定义错误
     */
    UN_KNOWN(9999, "未定义错误"),
    /**
     * 必须是字符串
     */
    MUST_BE_STRING(1201, "必须是字符串"),
    /**
     * 缺乏必填参数
     */
    PARAMS_REQUIRED(2101, "缺乏必填参数"),
    /**
     * 没有找到相关的角色信息
     */
    RoleInfoNotFound(202461, "没有找到相关的角色信息"),
    /**
     * 您无权进行此操作
     */
    AccessDenied(403, "您无权进行此操作"),
    /**
     * 您无此功能权限
     */
    PermissionDenied(401, "您无此功能权限"),
    /**
     * 没有找到相关的项目阶段信息
     */
    ProjectStateNotFound(202365, "没有找到相关的项目阶段信息"),
    /**
     * 字段格式错误
     */
    FormatExtractFieldError(202371, "字段格式错误"),
    /**
     * 没有找到相关的项目字段信息
     */
    CustomFieldNotFound(202366, "没有找到相关的项目字段信息"),
    /**
     * 没有找到相关的集团信息
     */
    GroupInfoNotFound(202001, "没有找到相关的集团信息");

    private Integer code;
    private String message;

    ErrorNumEnum(Integer code, String message) {
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

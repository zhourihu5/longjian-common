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
    GroupInfoNotFound(202001, "没有找到相关的集团信息"),
    /**
     * 没有找到相关的项目类型信息
     */
    ProjectTypeNotFound(202355, "没有找到相关的项目类型信息"),
    /**
     * 编辑项目失败
     */
    EditProjectError(202353, "编辑项目失败"),
    /**
     * 没有找到相关的项目阶段信息
     */
    ProjectStageNotFound(202359, "没有找到相关的项目阶段信息"),
    /**
     * 用户名，密码或企业编码错误
     */

    UserNameOrPasswordError(1000, "用户名，密码或企业编码错误"),
    /**
     * 参数有误
     */
    ArgError(406, "参数有误"),
    /**
     * 参数有误
     */
    DeleteTeamError(202014, "删除公司失败"),
    /**
     * 编辑公司失败
     */
    EditTeamError(202013,"编辑公司失败"),
    /**
     *
     */
    EditUserPasswordError(202125,"修改密码失败"),
    /**
     * 没有找到相关的用户信息
     */
    UserInfoNotFound(202120,"没有找到相关的用户信息"),
    /**
     *修改用户集团角色失败
     */
    EditUserInGroupRoleError(202240,"修改用户集团角色失败"),
    /**
     * 没有找到相关的公司信息
     */
    TeamInfoNotFound(202011,"没有找到相关的公司信息"),

    /**
     * 新建公司失败
     */
    CreateTeamError(202012,"新建公司失败"),

    /**
     * 账号状态异常，暂时无法登录
     */
    UserStatusNotNormal(1001,"账号状态异常，暂时无法登录");

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

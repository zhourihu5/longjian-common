package com.longfor.longjian.common.req.feignClientRep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @description 获取信息dto
 * @author bazhandao
 * @date 2018/12/13 17:32
 * @since JDK1.8
 */
@Slf4j
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户工号
     */
    private String jobCode;

    /**
     * 用户类型:1-内部员工,2-供方门户,3-供方开发
     */
    private String userType;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 状态:1-可用,2-锁定
     */
    private String status;

}

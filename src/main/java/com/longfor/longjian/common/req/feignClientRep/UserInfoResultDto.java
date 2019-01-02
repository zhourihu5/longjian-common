package com.longfor.longjian.common.req.feignClientRep;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description 用户信息请求结果dto
 * @author bazhandao
 * @date 2018/12/13 17:35
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
public class UserInfoResultDto implements Serializable {

    private String code;

    private String msg;

    private UserInfoDto data;


}

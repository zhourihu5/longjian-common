package com.longfor.longjian.common.req.feignClientRep;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @description 请求token结果的dto
 * @author bazhandao
 * @date 2018/12/13 17:20
 * @since JDK1.8
 */
@Setter
@Getter
@ToString
public class TokenResultDto implements Serializable {

    private String code;

    private String msg;

    private Map<String, String> data;

}

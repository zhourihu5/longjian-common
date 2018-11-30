package com.longfor.longjian.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lipeishuai
 * @date 2018/11/30 10:44
 */

@Data
@Slf4j
@AllArgsConstructor
public class LjBaseRuntimeException extends RuntimeException{

    private Integer errorCode;
    private String  errorMsg;
}

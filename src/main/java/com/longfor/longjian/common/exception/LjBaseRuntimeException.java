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
public class LjBaseRuntimeException extends RuntimeException{
    
    public LjBaseRuntimeException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    private Integer errorCode;
    private String  errorMsg;
}

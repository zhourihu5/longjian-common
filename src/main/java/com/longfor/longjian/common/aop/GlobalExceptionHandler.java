package com.longfor.longjian.common.aop;

import com.longfor.longjian.common.base.LjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Wang on 2019/3/12.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public LjBaseResponse handlerBindException(BindException exception){

        StringBuilder errorMsg=new StringBuilder();
        exception.getAllErrors().forEach(x->errorMsg.append(x.getDefaultMessage()).append(","));
        log.info("errorMsg.toString()  :{}",errorMsg.toString());
        LjBaseResponse resultMsg = new LjBaseResponse(-9999,  "参数不能为空或者参数类型异常!","");

        return resultMsg;

    }
}

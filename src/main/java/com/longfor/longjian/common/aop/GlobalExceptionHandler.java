package com.longfor.longjian.common.aop;

import com.longfor.longjian.common.base.LjBaseResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Wang on 2019/3/12.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public LjBaseResponse handlerBindException(BindException exception){

        StringBuilder errorMsg=new StringBuilder();
        exception.getAllErrors().forEach(x->errorMsg.append(x.getDefaultMessage()).append(","));
        LjBaseResponse resultMsg = new LjBaseResponse(-9999,  errorMsg.toString(),"");

        return resultMsg;

    }
}

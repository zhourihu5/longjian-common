package com.longfor.longjian.common.exception;

import com.longfor.longjian.common.consts.ErrorNumEnum;

public class CommonRuntimeException extends RuntimeException {

    private String code;
    private String message;

    public CommonRuntimeException() {
    }

    public CommonRuntimeException(String message) {
        super(message);
    }

    public CommonRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonRuntimeException(Throwable cause) {
        super(cause);
    }

    public CommonRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonRuntimeException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public CommonRuntimeException(ErrorNumEnum errorNumEnum) {
        super(errorNumEnum.getMessage());
        this.code = errorNumEnum.getCode().toString();
        this.message = errorNumEnum.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}

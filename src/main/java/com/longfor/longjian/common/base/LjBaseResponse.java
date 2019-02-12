package com.longfor.longjian.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * lipeishuai on 2018/11/14.
 *
 * @param <T>
 * @author lipeishuai
 * @date 2018/11/14 13:09
 */
@Data
@NoArgsConstructor
public class LjBaseResponse<T> implements Serializable {
    private int result;
    private int code;
    private T data;
    private String message = "success";
    private long timestamp = System.currentTimeMillis() / 1000; // 符合原来的接口中的10位秒级时间戳
    private int dataClazz = 1; // 1:字典，2:列表，3:基础类型
    private Class<?> baseDateClazz; // 默认Integer，dataClazz为3是需指定

    public LjBaseResponse(int result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public LjBaseResponse(int result, T data) {
        this.result = result;
        this.data = data;
    }

    public LjBaseResponse(int code, int result, T data) {
        this.code = code;
        this.result = result;
        this.data = data;
    }

    public LjBaseResponse(int code, int result, T data, String message) {
        this.code = code;
        this.result = result;
        this.data = data;
        this.message = message;
    }

    public LjBaseResponse(T data) {
        this(0, data);
    }
}

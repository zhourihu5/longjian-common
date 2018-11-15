package com.longfor.longjian.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * lipeishuai on 2018/11/14.
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class LjBaseResponse<T> implements Serializable {
    private int result;
    private T data;

    public LjBaseResponse(int result, T data) {
        this.result=result;
        this.data=data;
    }

    public LjBaseResponse(T data) {
        this(0, data);
    }
}

package com.longfor.longjian.common.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * lipeishuai on 2018/11/14.
 *
 * @author lipeishuai
 * @date 2018/11/14 13:09
 * @param <T>
 */
@Data
@NoArgsConstructor
public class LjBaseResponse<T> implements Serializable {
    private int result;
    private T data;
    private long timestamp = System.currentTimeMillis()/1000; // 符合原来的接口中的10位秒级时间戳

    public LjBaseResponse(int result, T data) {
        this.result=result;
        this.data=data;
    }

    public LjBaseResponse(T data) {
        this(0, data);
    }
}

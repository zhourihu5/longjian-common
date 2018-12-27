package com.longfor.longjian.common.util;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static final TimeUnit unit = TimeUnit.HOURS;

    @Resource
    private RedisTemplate redisTemplate;

    public void set(Object key, Object val) {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        operations.set(key, val);
    }

    public void setHash(Object key, Map<Object, Object> val) {
        setHash(key, val, null, null);
    }

    public void setHash(Object key, Map<Object, Object> val, Long timeout, TimeUnit unit) {
        HashOperations<Object, Object, Object> operations = redisTemplate.opsForHash();
        operations.putAll(key, val);
        if (timeout != null) {
            if (unit == null) {
                unit = RedisUtil.unit;
            }
            redisTemplate.expire(key, timeout, unit);
        }
    }

    public Object getHash(Object key, Object hashKey) {
        HashOperations<Object, Object, Object> operations = redisTemplate.opsForHash();
        return operations.get(key, hashKey);
    }

}

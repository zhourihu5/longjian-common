package com.longfor.longjian.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
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

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public Object get(Object key) {
        return redisTemplate.boundValueOps(key).get();
    }

    public long lGetListSize(String key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public long lRemove(String key,long count,Object value){
        try{
            Long remove = redisTemplate.opsForList().remove(key,count,value);
                return remove;
        }catch(Exception e){
            e.printStackTrace();
                return 0;
}
}

    public long deleteHash(Object key, Object val) {
        try {
            Long delete = redisTemplate.opsForHash().delete(key, val);
            return delete;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
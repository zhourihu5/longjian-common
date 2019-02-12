package com.longfor.longjian.common.util;

import com.longfor.gaia.gfs.data.redis.RedisKey;
import com.longfor.longjian.common.entity.UserBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

/**
 * 目前只支持一个redis数据源，若使用多数据源需进行线程处理
 */
@Component
@Slf4j
public class SessionInfo {

    private static String sessionPrefix;

    @Value("${session.prefix}")
    public void setSessionPrefix(String sessionPrefix) {
        SessionInfo.sessionPrefix = sessionPrefix;
    }

    @Autowired
    private RedisUtil redisUtil;

    public Object getBaseInfo(String key) {
        Object val = null;

        String token = getToken();
        if (StringUtils.isNotBlank(token)) {
            val = redisUtil.getHash(SessionInfo.getTokenKey(token, Boolean.TRUE), key);
        }
        return val;
    }

    public void setBaseInfo(String key, Object val) {
        String token = getToken();
        if (StringUtils.isNotBlank(token)) {
            redisUtil.setHash(SessionInfo.getTokenKey(token, Boolean.TRUE), key, val);
        }
    }

    public UserBase getSessionUser() {
        return (UserBase) getBaseInfo("user");
    }

    public static String getTokenKey(String token, boolean isDecode) {
        if (isDecode) {
            token = AESUtil.AESDecode(token);
        }
        return RedisKey.join(sessionPrefix, token);
    }

    public static String createTokenKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getToken() {
        if (Objects.isNull(RequestContextHolder.getRequestAttributes())) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // PC端请求获取
        if (Objects.nonNull(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                String name = cookie.getName();
                if ("zjsess".equals(name)) {
                    log.info("cookie获取token" + cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        // APP端请求
        return  (String) request.getAttribute("token");
    }

}

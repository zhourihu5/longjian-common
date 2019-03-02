package com.longfor.longjian.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Request处理工具类
 *
 * @author zkm
 * @date 2019/1/2 14:12
 */
@Slf4j
public class RequestUtil {

    /**
     * 获取request中的所有请求参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllParams(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        return params;
    }

    public final static String getIpAddress(HttpServletRequest request) {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取Header信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = Maps.newHashMap();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

    /**
     * 获取request中的所有参数并UrlEncode编码
     * 组装成k=v&k=v&k=v的形式
     *
     * @param request
     * @return
     */
    public static String encodeAllParams(HttpServletRequest request) {
        Map<String, String[]> values = request.getParameterMap();
        if (values == null || values.size() <= 0) {
            return StringUtils.EMPTY;
        }
        List<String> keys = Lists.newArrayList(values.keySet());
        Collections.sort(keys);
        StringBuilder buf = new StringBuilder();
        for (String k : keys) {
            String[] vs = values.get(k);
            try {
                String prefix = URLEncoder.encode(k, "UTF-8") + "=";
                for (String v : vs) {
                    if (buf.length() > 0) {
                        buf.append("&");
                    }
                    buf.append(prefix);
                    buf.append(URLEncoder.encode(v, "UTF-8"));
                }
            } catch (UnsupportedEncodingException e) {
                log.info("UrlEncode编码异常", e);
            }
        }
        return buf.toString();
    }

    /**
     * 从session中获取userId
     *
     * @param sessionInfo
     * @return
     */
    public static Integer getUserId(SessionInfo sessionInfo) {
        return (Integer) sessionInfo.getBaseInfo("userId");
    }
}

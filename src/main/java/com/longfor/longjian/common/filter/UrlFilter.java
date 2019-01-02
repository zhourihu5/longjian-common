package com.longfor.longjian.common.filter;

import com.longfor.longjian.common.util.SessionInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 修改请求路由，当进入url为/a/b时，将其url修改为/api/a/b
 * Created by qhong on 2018/5/16 13:27
 **/
@Component
public class UrlFilter implements Filter {

    private static Boolean aimEnable;

    @Value("${aim.enable}")
    public void setAimEnable(String aimEnable) {
        UrlFilter.aimEnable = Boolean.parseBoolean(aimEnable);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper((HttpServletResponse) response);
        String path = httpRequest.getRequestURI();
//        if (path.indexOf("/api") == 0) {
//            path = "/api" + path;
//            System.out.println(path);
//            httpRequest.getRequestDispatcher(path).forward(request, response);
//        } else {
//            chain.doFilter(request, response);
//
//        }
        if (!aimEnable) {
            request.setAttribute("noNeedFilter", "false");
        }
        System.out.println("请求路径：" + path);
        String token = httpRequest.getParameter("token");
        if (StringUtils.isNotBlank(token)) {
            request.setAttribute("noNeedFilter", "false");
            request.setAttribute("token", token);
        }
        token = httpRequest.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            request.setAttribute("noNeedFilter", "false");
            request.setAttribute("token", token);
        }
        chain.doFilter(request, response);
    }
}

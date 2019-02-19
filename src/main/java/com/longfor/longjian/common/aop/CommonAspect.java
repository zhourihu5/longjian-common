package com.longfor.longjian.common.aop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.longfor.longjian.common.base.LjBaseResponse;
import com.longfor.longjian.common.consts.DragonShardConstants;
import com.longfor.longjian.common.entity.UserBase;
import com.longfor.longjian.common.exception.CommonException;
import com.longfor.longjian.common.exception.CommonRuntimeException;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.filter.UrlFilter;
import com.longfor.longjian.common.util.CtrlTool;
import com.longfor.longjian.common.util.InitClassAttr;
import com.longfor.longjian.common.util.RequestContextHolderUtil;
import com.longfor.longjian.common.util.SessionInfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.NumberUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author chi.zhang
 * @version v1.0
 * @create 2018/6/9 下午5:24
 **/
@Aspect
@Configuration
@Component
public class CommonAspect {
    @Resource
    private CtrlTool ctrlTool;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Environment env;

    public CommonAspect(Environment env) {
        this.env = env;
    }

    private static Boolean aopEnable;

    @Value("${aop.enable}")
    public void setAopEnable(String aopEnable) {
        CommonAspect.aopEnable = Boolean.parseBoolean(aopEnable);
    }

    private PathMatcher pathMarch = new AntPathMatcher();

    @Autowired
    private SessionInfo sessionInfo;
    @Value("${longjian.white.list:}")
    private String whiteList;

    private final List<String> whites = Lists.newArrayList();

    @PostConstruct
    public void init() {
        if (StringUtils.isNotBlank(this.whiteList)) {
            whites.addAll(Arrays.asList(whiteList.split(",")));
        }
    }

    /**
     * Pointcut that matches all repositories, services and Web REST endpoints.
     */
    @Pointcut("within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.longfor.longjian..app.controller..*)")
    public void applicationPackagePointcut() {
    }

    /**
     * Advice that logs methods throwing exceptions.
     *
     * @param joinPoint join point for advice
     * @param e         exception
     */
    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        if (env.acceptsProfiles(DragonShardConstants.SPRING_PROFILE_DEVELOPMENT)) {
            log.error("异常: {}.{}() 原因 = \'{}\' 信息 = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);

        } else {
            log.error("异常: {}.{}() 原因 = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
        }
    }


    /**
     * Advice that logs when a method is entered and exited.
     *
     * @param joinPoint join point for advice
     * @return result
     * @throws Throwable throws IllegalArgumentException
     */
    @Around("applicationPackagePointcut() && springBeanPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (log.isDebugEnabled()) {
            log.debug("开始: {}.{}() 参数 = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), paramStr(joinPoint.getArgs()));
        }
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 0) {
            servletPath = servletPath + pathInfo;
        }

        if (StringUtils.isEmpty(servletPath)) {
            servletPath = request.getRequestURI();
        }
        try {
            UserBase userBase = sessionInfo.getSessionUser();
            if (userBase == null && !validWhite(servletPath) && aopEnable) {
                return new LjBaseResponse<>(401, -1, null, "token失效，请重新登录");
            }

            //设置当前项目，team
            setProjTeamGroupId(request);

            Object result = joinPoint.proceed();

            //处理返回为null问题
            initNull(result);

            if (log.isDebugEnabled()) {
                log.debug("结束: {}.{}() 结果 = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (Exception e) {
            log.error("参数异常: {} in {}.{}()", paramStr(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e);
//            if (e instanceof CommonException ||
//                    e instanceof CommonRuntimeException ||
//                    e instanceof LjBaseRuntimeException) {
//                return new LjBaseResponse<>(1, 1, e.getMessage());
////                throw e;
//            }
            return new LjBaseResponse<>(1, 1, null, e.getMessage());
//            throw new CommonException("服务器错误，请联系管理员");
        }
    }

    private void initNull(Object result) {
        if (Objects.nonNull(result) && LjBaseResponse.class.isAssignableFrom(result.getClass())) {
            InitClassAttr.init((LjBaseResponse) result);
        }
    }

    private void setProjTeamGroupId(HttpServletRequest request) {
        if (request.getParameter("project_id") != null && StringUtils.isNotBlank(request.getParameter("project_id"))) {
            try {
                Integer.valueOf(request.getParameter("project_id"));
                ctrlTool.projectRequired();
            } catch (Exception e) {
                log.debug("project_id 不能转换成int类型");
            }
        }
        if ((request.getParameter("team_id") != null && StringUtils.isNotBlank(request.getParameter("team_id"))) || (request.getParameter("groupId") != null && StringUtils.isNotBlank(request.getParameter("groupId")))) {
            try {
                if ((request.getParameter("team_id") != null)) {
                    Integer.valueOf(request.getParameter("team_id"));
                } else {
                    Integer.valueOf(request.getParameter("groupId"));
                }
                ctrlTool.teamRequired();
            } catch (Exception e) {
                log.debug("team_id 或 groupId不能转换成int类型");
            }
        }
    }

    private String paramStr(Object[] args) {
        List<Object> list = Lists.newArrayList();
        for (Object arg : args) {
            if (arg instanceof ServletRequest) {
                continue;
            }
            if (arg instanceof ServletResponse) {
                continue;
            }
            list.add(arg);
        }
        return JSON.toJSONString(list);
    }

    private boolean validWhite(String url) {
        for (String match : whites) {
            if (pathMarch.match(match, url.toLowerCase()))
                return true;
        }
        return false;
    }

}

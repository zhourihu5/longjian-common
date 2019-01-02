package com.longfor.longjian.common.feign;

import com.longfor.longjian.common.util.SessionInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Feign配置
 * 使用FeignClient进行服务间调用，传递headers信息
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    private static final String GAIA_API_HEADER = "X-Gaia-Api-Key";

    @Value("${iam.cas.server.gaia-api-key}")
    private String casGaiaApiKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 添加token
        requestTemplate.header("token", SessionInfo.getToken());
        // AIM头
        requestTemplate.header(GAIA_API_HEADER, casGaiaApiKey);
    }
}

package com.longfor.longjian.common.FeignClient;

import com.longfor.gaia.gfs.core.response.BaseResponse;
import com.longfor.gaia.gfs.web.feign.LFFeignClient;
import com.longfor.gaia.gfs.web.feign.config.LFFeignConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@LFFeignClient(group = "cas-server",value = "cas",configuration = LFFeignConfiguration.class)
public interface CasServerService {

    /**
     * 根据用户名密码获取token,结果
     * @param username
     * @param password
     * @param sysCode
     * @return
     */
    @PostMapping(value = "/cas/validatePasswordCreateTgt" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    byte[] authAndGetToken(@RequestParam(value = "username", required = false) String username,
                                                 @RequestParam(value = "password", required = false) String password,
                                                 @RequestParam(value = "sysCode", required = false) String sysCode);


    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @PostMapping(value = "/cas/oauth2.0/profile" ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    byte[] getUserInfoByToken(String token);
}

package com.longfor.longjian.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.longfor.longjian.common.geetest.GeetestConfig;
import com.longfor.longjian.common.geetest.GeetestLib;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
public class VerifyCodeUtil {
    private static final String LOGIN_FAIL_IP = "login_fail_{0}";
    private static final String LOGIN_INHIBIT_IP = "login_inhibit_{0}";

    @Resource
    private RedisUtil redisUtil;
    @Value("${verify.max_account_count}")
    private Integer maxAccountCount;
    @Value("${verify.max_fail_count}")
    private Integer maxFailCount;
    @Value("${verify.login_fail_ttl}")
    private Integer loginFailTtl;
    @Value("${verify.login_inhibit_ttl}")
    private Integer loginInhibitTtl;
    @Value("${verify.login_account_ttl}")
    private Integer loginAccountTtl;
    @Resource
    private GeetestConfig geetestConfig;

    public Map<String, Object> register() {
        Long statusTtl = 60 * 60 * 24L;
        GeetestLib gtSdk = new GeetestLib(geetestConfig.getGeetestId(), geetestConfig.getGeetestKey(),
                geetestConfig.isNewfailback());

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式

        //进行验证预处理
        int status = gtSdk.preProcess(param);
        redisUtil.setEx(gtSdk.gtServerStatusSessionKey, status, statusTtl, TimeUnit.SECONDS);
        return JSON.parseObject(gtSdk.getResponseStr());
    }

    public int isTrigger(String ip, String userName) {
        String name = MessageFormat.format(VerifyCodeUtil.LOGIN_FAIL_IP, ip);
        Map<String, List<Long>> failLogs = redisUtil.getHashObject(name, Map.class);
        log.debug("登录错误记录", failLogs);
        int result = 0;
        if (MapUtils.isNotEmpty(failLogs)) {
            List<String> accounts = Lists.newArrayList();
            failLogs.forEach((username, timestamps) -> {
                if (Objects.nonNull(timestamps)) {
                    accounts.add(username);
                }
            });
            log.debug("失败的账号", accounts);
            if (accounts.size() >= maxAccountCount) {
                log.debug("账号超过5个");
                result = 1;
            }
            int mfc = failLogs.values().stream().filter(Objects::nonNull)
                    .max(Comparator.comparingInt(List::size)).orElse(Lists.newArrayList()).size();
            log.debug("单账号最高失败次数：{}", mfc);
            if (mfc >= maxFailCount) {
                log.debug("单账号超过3次");
                result = 1;
            }
            if (StringUtils.isNotBlank(userName)) {
                List<Long> timestampList = (List<Long>) redisUtil.getHash(name, userName);
                log.debug(JSON.toJSONString(timestampList));
                if (CollectionUtils.isEmpty(timestampList)) {
                    result = 0;
                }

            }
        }
        return result;
    }

    public int validate(String challenge, String validate, String seccode) {
        GeetestLib gtSdk = new GeetestLib(geetestConfig.getGeetestId(), geetestConfig.getGeetestKey(),
                geetestConfig.isNewfailback());

        //从session中获取gt-server状态
        int gtServerStatusCode = (int) redisUtil.get(gtSdk.gtServerStatusSessionKey);

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<>();
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式

        int gtResult = 0;

        if (gtServerStatusCode == 1) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            log.debug("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }
        return gtResult;
    }

    public void setFailAccount(String ip, String userName) {
        String name = MessageFormat.format(VerifyCodeUtil.LOGIN_FAIL_IP, ip);
        if (!redisUtil.exists(name)) {
            List<Long> timestampList = Lists.newArrayList();
            timestampList.add(new Date().getTime() + loginAccountTtl);
            redisUtil.setHash(name, userName, timestampList);
            redisUtil.setEx(name, loginFailTtl.longValue(), TimeUnit.MILLISECONDS);
        } else {
            List<Long> timestampList = getTimestampList(name, userName);
            timestampList.add(new Date().getTime() + loginAccountTtl);
            redisUtil.setHash(name, userName, timestampList);
        }
        setLoginInhibit(ip, userName);
    }

    public void resetFailAccount(String ip, String userName) {
        String name = MessageFormat.format(VerifyCodeUtil.LOGIN_FAIL_IP, ip);
        if (redisUtil.exists(name)) {
            redisUtil.setHash(name, userName, Lists.newArrayList());
        }
    }

    public void setLoginInhibit(String ip, String userName) {
        String failName = MessageFormat.format(VerifyCodeUtil.LOGIN_FAIL_IP, ip);
        String inhibitName = MessageFormat.format(VerifyCodeUtil.LOGIN_INHIBIT_IP, ip);
        if (!redisUtil.exists(inhibitName)) {
            DateTime now = DateTime.now();
            log.debug(now.toString("yyyy-MM-dd HH:ss:mm:SSS"));
            List<Long> timestampList = getTimestampList(failName, userName);
            List<Long> validTimestamps = timestampList.stream().filter(vo -> vo > now.getMillis()).collect(Collectors.toList());
            log.debug(JSON.toJSONString(validTimestamps));
            if (CollectionUtils.isNotEmpty(validTimestamps) && validTimestamps.size() % 10 == 0) {
                redisUtil.setEx(inhibitName, 1, loginInhibitTtl.longValue(), TimeUnit.MILLISECONDS);
            }
        }
    }

    public Integer getLoginInhibit(String ip) {
        Integer data = (Integer) redisUtil.get(MessageFormat.format(VerifyCodeUtil.LOGIN_INHIBIT_IP, ip));
        if (Objects.nonNull(data)) {
            return data;
        } else {
            return 0;
        }
    }

    public void resetLoginInhibit(String ip) {
        redisUtil.del(MessageFormat.format(VerifyCodeUtil.LOGIN_INHIBIT_IP, ip));
    }

    public Long getLoginInhibitTtl(String ip) {
        return redisUtil.ttl(MessageFormat.format(VerifyCodeUtil.LOGIN_INHIBIT_IP, ip));
    }

    private List<Long> getTimestampList(String name, String userName) {
        List<Long> timestampList;
        List<Long> timestamps = (List<Long>) redisUtil.getHash(name, userName);
        if (CollectionUtils.isEmpty(timestamps)) {
            timestampList = Lists.newArrayList();
        } else {
            timestampList = timestamps;
        }
        return timestampList;
    }

}

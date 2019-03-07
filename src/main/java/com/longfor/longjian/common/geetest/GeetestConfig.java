package com.longfor.longjian.common.geetest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.text.StyledEditorKit.BoldAction;

/**
 * GeetestWeb配置文件
 *
 *
 */
@Component
@Slf4j
public class GeetestConfig {

    @Value("${verify.geetest_id}")
    private String geetestId;
    @Value("${verify.geetest_key}")
    private String geetestKey;
    private boolean newfailback = true;

    public String getGeetestId() {
        return geetestId;
    }

    public String getGeetestKey() {
        return geetestKey;
    }

    public boolean isNewfailback() {
        return newfailback;
    }
}

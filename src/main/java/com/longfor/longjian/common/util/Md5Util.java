package com.longfor.longjian.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author zkm
 * @date 2018/12/21 19:41
 */
@Slf4j
public class Md5Util {

    /**
     * 获取MD5值
     *
     * @param data
     * @return
     */
    public static String md5Encode(byte[] data) {
        String hashString = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(data);
            hashString = new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            log.info("MD5加密工具异常", e);
        }
        return hashString.toLowerCase();
    }

}

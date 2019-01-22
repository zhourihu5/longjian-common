package com.longfor.longjian.common.util;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

/**
 * 密码生成及密码比对工具类
 *
 * @author zkm
 * @date 2019/1/10 15:52
 */
public class PasswordUtil {

    private static final String SEED = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#%^&*()_+=-[]{};:,./<>?";
    private static final Integer SALT_LEN = 24;
    private static final String SEP = "$";

    /**
     * 生成加密后的密码
     *
     * @param password
     * @return
     */
    public static String generatePassword(String password) {
        String salt = generateRandomSalt();
        return salt + SEP + md5(md5(password) + salt);
    }

    /**
     * 判断密码是否正确
     *
     * @param password
     * @param dbPassword
     * @return
     */
    public static boolean checkPassword(String password, String dbPassword) {
        List<String> dbPwdArr = StringUtil.strToStrs(dbPassword, "\\" + SEP);
        String dbSalt = dbPwdArr.get(0);
        String dbPwdMd5 = dbPwdArr.get(1);
        String md5Pwd = md5(md5(password.trim()) + dbSalt);
        return dbPwdMd5.equals(md5Pwd);
    }

    /**
     * 生成随机盐
     *
     * @return
     */
    private static String generateRandomSalt() {
        StringBuffer saltBuffer = new StringBuffer();
        Random random = new Random();
        char[] seeds = SEED.toCharArray();
        int i = 0;
        while (i < SALT_LEN) {
            int index = random.nextInt(seeds.length);
            saltBuffer.append(seeds[index]);
            i++;
        }
        return saltBuffer.toString();
    }

    /**
     * 普通MD5加密
     *
     * @param pwd
     * @return
     */
    private static String md5(String pwd) {
        return Md5Util.md5Encode(pwd.getBytes(StandardCharsets.UTF_8));
    }

}

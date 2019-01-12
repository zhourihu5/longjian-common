package com.longfor.longjian.common.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

/**
 * 用户密码加密生成
 * @auythor yanglei
 */
public class PasswordMd5Util {
    public static String genSaltedPassword(String password){
        byte[] saltedBytes=new byte[16];
        Random ran=new Random();
        for(int i=0;i<saltedBytes.length;i++){
            saltedBytes[i]=new Integer(ran.nextInt(122-48)+48).byteValue();
        }
        String salt=new String(saltedBytes);
        return salt+"$"+DigestUtils.md5Hex(DigestUtils.md5Hex(password)+salt);
    }
}

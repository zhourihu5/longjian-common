package com.longfor.longjian.common.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * AES的加密和解密
 * @author libo
 */
@Slf4j
public class AESForeEnd {
    //使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
    private static final String KEY = "dufy20170329java";

    private static final String IV = "dufy20170329java";

    /**
     * 加密方法
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        try {

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return URLEncoder.encode(new Base64().encodeToString(encrypted), StandardCharsets.UTF_8.toString());

        } catch (Exception e) {
            log.error("登录密码加密失败" + e);
            return null;
        }
    }

    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     */
    public static String desEncrypt(String data, String key, String iv) {
        try {
            data = URLDecoder.decode(data, StandardCharsets.UTF_8.toString());
            byte[] encrypted1 = new Base64().decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            return new String(original);
        } catch (Exception e) {
            log.error("登录密码解密失败" + e);
            return null;
        }
    }

    /**
     * 使用默认的key和iv加密
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return encrypt(data, KEY, IV);
    }

    /**
     * 使用默认的key和iv解密
     * @param data
     * @return
     */
    public static String desEncrypt(String data) {
        return desEncrypt(data, KEY, IV);
    }

//    public static void main(String args[]) {
//
//        String test = "18729990110";
//
//        String data = null;
//        String key = "dufy20170329java";
//        String iv = "dufy20170329java";
//
//        data = encrypt(test, key, iv);
//
//        System.out.println(data);
//        System.out.println(desEncrypt(data, key, iv));
//    }
}

package com.longfor.longjian.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author yangzhao
 * @Description
 * @Date create by 15:49 18/2/3
 */
@Slf4j
@Component
public class AES_CBC {
    /**
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static String encodeRules = "LONGJIANHAHAHAHA";
    private static final String ivParameter = "1234567890ABCDEF";
    private static final String AES_MODAL = "AES/CBC/PKCS5Padding";

//    private static String encodeRules;

//    @Value("${key.pairs}")
    public void setEncodeRules(String encodeRules) {
        AES_CBC.encodeRules = encodeRules;
    }

    // 加密
    public static String AESEncode(String sSrc) {
        try {
            byte[] raw = encodeRules.getBytes();
            Cipher cipher = Cipher.getInstance(AES_MODAL);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getUrlEncoder().encode(encrypted));//此处使用BASE64做转码。
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    // 解密
    public static String AESDecode(String sSrc) {
        try {
            byte[] raw = encodeRules.getBytes(StandardCharsets.US_ASCII);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(AES_MODAL);
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getUrlDecoder().decode(sSrc);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    public static void main(String[] args) throws Exception {
        String a = AESEncode("d75c3c6bab6d4d2ab69e1d0ae0ed60c3");
        System.out.println(a);
        String b = AESDecode(a);
        System.out.println(b);
    }
}

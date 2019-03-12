package com.longfor.longjian.common.util;

import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/** * @description RSA工具类 * @author bazhandao * @date 2018/12/26 18:40 * @since JDK1.8 */
public class RSAUtil {

    /** * RSA加密的方法 * @author bazhandao * @date 2018-12-27 * @return rsa加密后转换成的base64加密字符串 */
    public static String encrypt(String password, String publicKeyStr) throws LjBaseRuntimeException {
        try {
            BASE64Decoder b64d = new BASE64Decoder();
            byte[] keyByte = b64d.decodeBuffer(publicKeyStr);
            X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509ek);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] sbt = password.getBytes();
            byte[] epByte = cipher.doFinal(sbt);
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(epByte); 
        } catch (Exception e) {
           throw new LjBaseRuntimeException(-9999,e+"");
        }
        
    }
}

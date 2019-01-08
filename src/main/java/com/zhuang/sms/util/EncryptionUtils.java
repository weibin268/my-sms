package com.zhuang.sms.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by zhuang on 9/2/2017.
 */
public class EncryptionUtils {

    public final static byte[] encryptByMD5(byte[] bytes){

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw  new RuntimeException("MessageDigest.getInstance(\"MD5\") error!");
        }

        messageDigest.update(bytes);

        return messageDigest.digest();
    }

    public final static String encryptByMD5(String s) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        byte[] btInput = s.getBytes();

        byte[] md = encryptByMD5(btInput);

        int j = md.length;

        char str[] = new char[j * 2];

        int k = 0;

        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }

        return new String(str);

    }

    public final static String encryptByAES(String content, String key) {

        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            keyGenerator.init(128, new SecureRandom(key.getBytes()));

            SecretKey originalKey = keyGenerator.generateKey();

            byte[] keyBytes = originalKey.getEncoded();

            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encodeBytes = content.getBytes("utf-8");

            byte[] AESBytes = cipher.doFinal(encodeBytes);

            String result = new String(new BASE64Encoder().encode(AESBytes));

            return result;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    public final static String decryptByAES(String content, String key) {

        try {

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

            keyGenerator.init(128, new SecureRandom(key.getBytes()));

            SecretKey originalKey = keyGenerator.generateKey();

            byte[] keyBytes = originalKey.getEncoded();

            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(content);

            byte[] decodeBytes = cipher.doFinal(decodeBuffer);

            String result = new String(decodeBytes, "utf-8");

            return result;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

}

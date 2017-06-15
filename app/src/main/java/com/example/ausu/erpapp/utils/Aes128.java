package com.example.ausu.erpapp.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes128 {

    //加密的算法及补码方式
    private static final String AES = "AES/CBC/PKCS7Padding";

    //    private static final String CRYPT_KEY = "aUBSdwfg6u8#sfY*";
    private static final String CRYPT_KEY = "1234567891231111";


    //补码偏移量
    private static final String AES_IV = "0392039203920300";


    /**
     * 加密
     *
     * @param encryptStr
     * @return
     */
    public static byte[] encrypt(byte[] src, String key) throws Exception {
        //
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), "AES");
        //使用的是cbc模式，所以需要一个向量iv
        IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, securekey, ivParameterSpec);// 设置密钥和加密形式
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param decryptStr
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), "AES");
        //设置补码偏移量
        IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, securekey, ivParameterSpec);// 设置密钥和解密形式
        return cipher.doFinal(src);
    }

    /**
     * 二行制转十六进制字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * 解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public final static String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    CRYPT_KEY));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public final static String encrypt(String data) {
        try {
            return byte2hex(encrypt(data.getBytes(), CRYPT_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

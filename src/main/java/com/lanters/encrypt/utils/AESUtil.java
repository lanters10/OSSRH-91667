package com.lanters.encrypt.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dp
 * @Description AES工具
 * @date 2023/5/16 9:40
 */
@Slf4j
public class AESUtil {

    public static String decryptUtilsFunc(String response, String key) {
        AES aes1 = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        String decryptStr = null;
        try {
            decryptStr = aes1.decryptStr(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptStr;
    }

    public static String encryptUtilsFunc(String response, String key) {
        AES aes1 = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        String encryptStr = null;
        try {
            encryptStr = aes1.encryptBase64(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptStr;
    }
}

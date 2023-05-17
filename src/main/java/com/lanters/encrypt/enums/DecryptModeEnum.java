package com.lanters.encrypt.enums;

import cn.hutool.core.util.StrUtil;
import com.lanters.encrypt.properties.RSAProperties;
import com.lanters.encrypt.utils.AESUtil;
import com.lanters.encrypt.properties.ApiEncryptProperties;
import com.lanters.encrypt.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
public enum DecryptModeEnum {

    RSA {
        @Override
        public String decrypt(String value, ApiEncryptProperties apiEncryptProperties) {
            RSAProperties rsaProperties = apiEncryptProperties.getRsa();
            String privateKey = rsaProperties.getPrivateKey();
            StringBuilder decryptStr = new StringBuilder();
            try {
                if (StrUtil.isAllBlank(privateKey)) {
                    throw new NullPointerException("privateKey is not null !");
                }
                value = value.replaceAll(" ", "+");
                if (!StringUtils.isEmpty(value)) {
                    String[] split = value.split("\\|");
                    for (String item : split) {
                        item = new String(RSAUtil.decrypt(RSAUtil.decode(item), rsaProperties.getPrivateKey()), StandardCharsets.UTF_8);
                        decryptStr.append(item);
                    }
                }
            } catch (Exception e) {
                log.error("RSA Dencrypt Exception", e);
            }
            return decryptStr.toString();
        }
    },

    AES {
        @Override
        public String decrypt(String value, ApiEncryptProperties apiEncryptProperties) {
            return AESUtil.decryptUtilsFunc(value, apiEncryptProperties.getAes().getKey());
        }
    };

    public abstract String decrypt(String value, ApiEncryptProperties apiEncryptProperties);
}

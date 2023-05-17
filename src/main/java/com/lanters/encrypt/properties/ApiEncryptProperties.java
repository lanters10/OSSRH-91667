package com.lanters.encrypt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "api.encrypt")
@Configuration
@Data
public class ApiEncryptProperties {

    private AESProperties aes = new AESProperties();

    private RSAProperties rsa= new RSAProperties();
}

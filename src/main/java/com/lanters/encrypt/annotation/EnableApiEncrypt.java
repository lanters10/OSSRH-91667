package com.lanters.encrypt.annotation;

import com.lanters.encrypt.aspect.DecryptAspect;
import com.lanters.encrypt.aspect.EncryptAspect;
import com.lanters.encrypt.properties.ApiEncryptProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author dp
 * @Description 启用API接口请求参数加解密中间件
 * @date 2023/5/16 9:25
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({ApiEncryptProperties.class,
        EncryptAspect.class,
        DecryptAspect.class})
public @interface EnableApiEncrypt {

}

package com.lanters.encrypt.annotation;

import com.lanters.encrypt.enums.EncryptModeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dp
 * @Description 对象属性解密注解
 * @date 2023/5/16 9:27
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {

    String[] value() default "";

    EncryptModeEnum encryptMode() default EncryptModeEnum.AES;
}

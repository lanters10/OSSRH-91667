package com.lanters.encrypt.annotation;

import com.lanters.encrypt.enums.DecryptModeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dp
 * @Description 对象属性加密注解
 * @date 2023/5/16 9:27
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptField {

    String[] value() default "";

    DecryptModeEnum decryptMode() default DecryptModeEnum.AES;
}

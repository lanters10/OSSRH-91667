package com.lanters.encrypt.aspect;

import cn.hutool.core.util.ReflectUtil;
import com.lanters.encrypt.annotation.DecryptField;
import com.lanters.encrypt.enums.DecryptModeEnum;
import com.lanters.encrypt.properties.ApiEncryptProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author dp
 * @Description 加密切面逻辑
 * @date 2023/5/16 9:29
 */
@Slf4j
@Aspect
@Component
public class DecryptAspect {

    @Autowired
    private ApiEncryptProperties apiEncryptProperties;

    @Pointcut("@annotation(com.lanters.encrypt.annotation.OpenDecrypt)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //解密（DTO）
        decrypt(joinPoint);
        return joinPoint.proceed();
    }

    public void decrypt(ProceedingJoinPoint joinPoint) {
        Object[] objects = null;
        try {
            objects = joinPoint.getArgs();
            for (Object object : objects) {
                if (object instanceof Serializable) {
                    decryptObject(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void decryptObject(Object obj) throws IllegalAccessException {
        if (Objects.isNull(obj)) {
            return;
        }
        Field[] allFields = ReflectUtil.getFields(obj.getClass());
        for (Field field : allFields) {
            boolean containEncryptField = field.isAnnotationPresent(DecryptField.class);
            if (containEncryptField) {
                field.setAccessible(true);
                DecryptField decryptField = field.getAnnotation(DecryptField.class);
                DecryptModeEnum decryptModeEnum = decryptField.decryptMode();
                String value = decryptModeEnum.decrypt(String.valueOf(field.get(obj)), apiEncryptProperties);
                field.set(obj, value);
            }
        }
    }
}

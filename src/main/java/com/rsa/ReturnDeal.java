package com.rsa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AOP注解 接口返回加密
 *
 * @author zfx
 * @title: ReturnDeal
 * @date 2022/5/20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnDeal {


}
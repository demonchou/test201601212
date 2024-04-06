package com.demonchou.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * dubbo异常处理切面注解
 * 
 * copy from epay
 * 
 * huangxuwei
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CouponProxyExceptionReturn
{

}

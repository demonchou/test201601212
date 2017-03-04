package com.demonchou.common.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:bean中文名注解
 * Created by zhouhongfei on 2017/3/4 12:53.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface FieldName
{

	String value();

}

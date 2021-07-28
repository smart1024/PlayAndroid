package com.lilin.android.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建日期：2021/7/28 09:31
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.demo
 * 类说明：自定义AOP注解，作为Pointcut切入点
 */

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface DebugTool {
}

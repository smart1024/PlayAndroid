package com.lilin.android.laop_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建日期：2021/7/27 15:18
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.laop_annotation
 * 类说明：
 */

/**
 * RetentionPolicy.SOURCE 注解只保留在源文件，当编译为class文件时，被遗弃
 * RetentionPolicy.CLASS  注解保留到字节码，被jvm加载后被遗弃
 * RetentionPolicy.RUNTIME 注解保留到字节码，被jvm加载之后，仍然存在
 * 这3个生命周期分别对应于：Java源文件(.java文件) —> .class文件 —> 内存中的字节码。
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR})
public @interface DebugLog {
    /**
     * @return 日志的优先级(默认是0)
     */
    int priority() default 0;
}

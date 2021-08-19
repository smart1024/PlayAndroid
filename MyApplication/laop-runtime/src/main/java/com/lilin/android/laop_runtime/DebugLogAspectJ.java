package com.lilin.android.laop_runtime;

import android.os.Build;
import android.os.Looper;
import android.os.Trace;

import androidx.annotation.NonNull;

import com.lilin.android.laop_annotation.DebugLog;
import com.lilin.android.laop_runtime.logger.XLogger;
import com.lilin.android.laop_runtime.util.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.concurrent.TimeUnit;

/**
 * 创建日期：2021/8/2 11:03
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.laop_runtime
 * 类说明：
 * https://blog.csdn.net/zhongwn/article/details/80410313?utm_source=blogxgwz8
 * https://www.jianshu.com/p/044c149caf7f
 */

@Aspect
public class DebugLogAspectJ {
    /*
    带有注解类DebugLog修饰的类的所有JPoint（方法中不一定有DebugLog修饰）
    匹配带有DebugLog注解的所有JPoint
    */
    @Pointcut("within(@com.lilin.android.laop_annotation.DebugLog *)")
    public void withinAnnotatedClass() {
    }

    /*
    synthetic 是内部类编译后添加的修饰语，所以 !synthetic 表示非内部类的
    匹配带有注解DebugLog修饰的所有JPoint但不包括被synthetic修饰的方法(不包含编译器生成的方法)
     */
    @Pointcut("execution(!synthetic * * (..) && withinAnnotatedClass())")
    public void methodInsideAnnotatedType() {

    }

    /*
    匹配有注解DebugLog修饰的JPoint但不包括编译器生成synthetic关键字修饰new调用
    */
    @Pointcut("execution(!synthetic *.new(..)) && withinAnnotatedClass()")
    public void constructorInsideAnnotatedType(){

    }

    /*
    匹配在执行状态中且被DebugLog注解方法
    匹配被DebugLog注解修饰的所有方法或者
    匹配带有注解DebugLog修饰的所有JPoint但不包括被synthetic修饰的方法(不包含编译器生成的方法)
     */
    @Pointcut("execution(@com.lilin.android.laop_annotation.DebugLog * *(..)) || methodInsideAnnotatedType()")
    public void method() {
    }

    /**
     * 匹配被DebugLog注解修饰的所有构造方法或者被DebugLog注解的类中所有的JPoint(但不包括编译器生成synthetic关键字修饰new调用)
     */
    @Pointcut("execution(@com.lilin.android.laop_annotation.DebugLog *.new(..)) || constructorInsideAnnotatedType()")
    public void constructor() {
    }

    @Around("(method() || constructor()) && @annotation(debugLog)")
    public Object logAndExecute(ProceedingJoinPoint joinPoint, DebugLog debugLog) throws Throwable {
        enterMethod(joinPoint, debugLog);

        long startNanos = System.nanoTime();
        Object result = joinPoint.proceed();
        long stopNanos = System.nanoTime();
        long lengthMillis = TimeUnit.NANOSECONDS.toMillis(stopNanos - startNanos);

        exitMethod(joinPoint, debugLog, result, lengthMillis);

        return result;
    }

    /**
     * 方法执行前切入
     *
     * @param joinPoint
     */
    private void enterMethod(ProceedingJoinPoint joinPoint, DebugLog debugLog) {
        if (!XLogger.isDebug()) {
            return;
        }
        //切入点获取切入类型
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        Class<?> cls = codeSignature.getDeclaringType(); //方法所在类
        String methodName = codeSignature.getName();    //方法名
        String[] parameterNames = codeSignature.getParameterNames(); //方法参数名集合
        Object[] parameterValues = joinPoint.getArgs(); //方法参数集合

        //记录并打印方法的信息
        StringBuilder builder = getMethodLogInfo(methodName, parameterNames, parameterValues);

        XLogger.log(debugLog.priority(), Utils.getClassName(cls), builder.toString());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            final String section = builder.substring(2);
            Trace.beginSection(section);
        }
    }

    /**
     * 获取方法的日志信息
     *
     * @param methodName      方法名
     * @param parameterNames  方法参数名集合
     * @param parameterValues 方法参数值集合
     * @return
     */
    @NonNull
    private StringBuilder getMethodLogInfo(String methodName, String[] parameterNames, Object[] parameterValues) {
        StringBuilder builder = new StringBuilder("\u21E2 ");
        builder.append(methodName).append('(');
        for (int i = 0; i < parameterValues.length; i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(parameterNames[i]).append('=');
            builder.append(Utils.toString(parameterValues[i]));
        }
        builder.append(')');

        if (Looper.myLooper() != Looper.getMainLooper()) {
            builder.append(" [Thread:\"").append(Thread.currentThread().getName()).append("\"]");
        }
        return builder;
    }


    /**
     * 方法执行完毕，切出
     *
     * @param joinPoint
     * @param result       方法执行后的结果
     * @param lengthMillis 执行方法所需要的时间
     */
    private void exitMethod(ProceedingJoinPoint joinPoint, DebugLog debugLog, Object result, long lengthMillis) {
        if (!XLogger.isDebug()) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.endSection();
        }

        Signature signature = joinPoint.getSignature();

        Class<?> cls = signature.getDeclaringType();
        String methodName = signature.getName();

        boolean hasReturnType = Utils.isHasReturnType(signature);

        StringBuilder builder = new StringBuilder("\u21E0 ")
                .append(methodName)
                .append(" [")
                .append(lengthMillis)
                .append("ms]");

        if (hasReturnType) {
            builder.append(" = ");
            builder.append(Utils.toString(result));
        }

        XLogger.log(debugLog.priority(), Utils.getClassName(cls), builder.toString());
    }

}

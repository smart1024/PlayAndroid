package com.lilin.android.demo;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 创建日期：2021/7/27 17:52
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.demo
 * 类说明：要理解代码下面几条必读
 * 1、@Before：Advice，也就是具体的插入点
 * 2、execution：处理Join Point的类型，例如call、execution
 * 3、(* android.app.Activity.on**(..))：这个是最重要的表达式，第一个『*』表示返回值，『*』表示返回值为任意类型，
 * 后面这个就是典型的包名路径，其中可以包含『*』来进行通配，几个『*』没区别。同时，这里可以通过『&&、||、!』来进行条件组合。
 * ()代表这个方法的参数，你可以指定类型，例如android.os.Bundle，或者(..)这样来代表任意类型、任意个数的参数。
 * 4、public void onActivityMethodBefore：实际切入的代码
 * 参考链接:https://www.jianshu.com/p/5c9f1e8894ec
 */
@Aspect
public class AspectTest {
    private static final String TAG = "AspectTest";

    /**
     * 这execution指定的方法之前调用
     * @param joinPoint
     * @throws Throwable
     */
//    @Before("execution(* android.app.Activity.on*(..))")
//    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
//        String key = joinPoint.getSignature().toString();
//        Log.e(TAG, "onActivityMethodBefore: " + key);
//    }

    @Before("execution(* com.lilin.android.demo.MainActivity.on*(android.os.Bundle))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "onActivityMethodBefore: " + key);
    }

    @After("execution(* com.lilin.android.demo.MainActivity.on*(android.os.Bundle))")
    public void onActivityMethodAfter(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "onActivityMethodAfter: " + key);
    }
}

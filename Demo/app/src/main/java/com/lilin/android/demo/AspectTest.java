package com.lilin.android.demo;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 创建日期：2021/7/27 17:52
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.demo
 * 类说明：要理解代码下面几条必读
 *
 *  Pointcuts切入点
 * 1、@Before：Advice，也就是具体的插入点
 * 2、execution：处理Join Point的类型，例如call、execution
 * 3、(* android.app.Activity.on**(..))：这个是最重要的表达式，第一个『*』表示返回值，『*』表示返回值为任意类型，
 * 后面这个就是典型的包名路径，其中可以包含『*』来进行通配，几个『*』没区别。同时，这里可以通过『&&、||、!』来进行条件组合。
 * ()代表这个方法的参数，你可以指定类型，例如android.os.Bundle，或者(..)这样来代表任意类型、任意个数的参数。
 * 4、public void onActivityMethodBefore：实际切入的代码
 * 参考链接:https://www.jianshu.com/p/5c9f1e8894ec
 *
 * call、execution类似，都是插入代码的意思，
 * 区别就是execution是在被切入的方法中，
 * call是在调用被切入的方法前或者后
 * //对于Call来说：
 * Call（Before）
 * Pointcut{
 *     Pointcut Method
 * }
 * Call（After）
 *
 * //对于Execution来说：
 * Pointcut{
 *   execution（Before）
 *     Pointcut Method
 *   execution（After）
 * }
 */
@Aspect
public class AspectTest {
    private static final String TAG = "AspectTest";

    /**
     * 这execution指定的方法之前调用
     * @param joinPoint
     * @throws Throwable
     * 监听所有以android.app.Activity.on开头的方法，包括子类
     */
    @Before("execution(* android.app.Activity.on*(..))")
    public void onMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "onActivityMethodBefore: " + key);
    }

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

    /**
     * Around确实实现了Before和After的功能，但是要注意的是，
     * Around和After是不能同时作用在同一个方法上的，会产生重复切入的问题
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("execution(* com.lilin.android.demo.MainActivity.testAOP())")
    public void onActivityMethodAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = proceedingJoinPoint.getSignature().toString();
        Log.e(TAG, "onActivityMethodAroundFirst: " + key);
        //调用原始方法
        proceedingJoinPoint.proceed();
        Log.e(TAG, "onActivityMethodAroundSecond: " + key);
    }

    /**
     * 先定义Pointcuts，并声明要监控的方法名 * * 第一个*代码匹配任意访问权限,第二*匹配任意返回值，必不可少否则报错
     * 然后在Before或者其它Advice里面添加切入代码，即可完成切入
     */
    @Pointcut("execution(@com.lilin.android.demo.DebugTool * *(..))")
    public void DebugToolMethod() {
    }

    @Before("DebugToolMethod()")
    public void onDebugToolMethodBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "onDebugToolMethodBefore: " + key);
    }



    //定义一个使用该注解的Pointcut
    @Pointcut("execution(@com.lilin.android.demo.AspectAnnotation * *(..))")
    public void AspectAnnotation(){

    }

    @Before("AspectAnnotation()")
    public void testAspectAnnotation(JoinPoint point){
        Log.e(TAG, point.getSignature().getName() + "-Before ");
    }

    /**
     * JoinPoint call 类型
     * @param joinPoint
     * @throws Throwable
     * Call（Before）
     */
    @Before("call(* com.lilin.android.demo.MainActivity.testCall())")
    public void methodTestCall(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "methodTestCall: " + key);
    }

    // 在testWithInCode2方法内
    @Pointcut("withincode(* com.lilin.android.demo.MainActivity.testWithInCode2())")
    public void invokeWithInCode2() {
    }

    // 在printWithInCode方法内,被testWithInCode1和testWithInCode2都调用的方法拦截
    @Pointcut("call(* com.lilin.android.demo.MainActivity.printWithInCode())")
    public void invokePrintWithInCode() {
    }

    /**
     * 仅testWithInCode2被调用时，才切入代码
     */
    @Pointcut("invokeWithInCode2() && invokePrintWithInCode()")
    public void invokeAOPOnlyInTestWithInCode2(){

    }

    @Before("invokeAOPOnlyInTestWithInCode2()")
    public void beforeInvokePrintWithInCode2(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.e(TAG, "beforeInvokePrintWithInCode2: " + key);
    }

    @AfterThrowing(pointcut = "execution(* com.lilin.android.demo.*.*(..))", throwing = "exception")
    public void catchExceptionMethod(Exception exception) {
        String message = exception.toString();
        Log.e(TAG, "catchExceptionMethod: " + message);
    }
}

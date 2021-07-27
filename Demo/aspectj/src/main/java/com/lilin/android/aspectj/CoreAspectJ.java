package com.lilin.android.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 创建日期：2021/7/27 15:59
 *
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.aspectj
 * 类说明：
 */
@Aspect
public class CoreAspectJ {
    /*在切点方法之前调用*/
    @Before("call(* com.lilin.android.demo.MainActivity.showMsg(java.lang.String))")
    public void showBefore(JoinPoint joinPoint) {
        Log.e("CoreAspectJ", "AspectJ:牛B个锤子1");
    }
    /*在切点方法之后调用*/
    @After("call(* com.lilin.android.demo.MainActivity.showMsg(java.lang.String))")
    public void showAfter(JoinPoint joinPoint) {
        Log.e("CoreAspectJ", "AspectJ:牛B个锤子2");
    }
}

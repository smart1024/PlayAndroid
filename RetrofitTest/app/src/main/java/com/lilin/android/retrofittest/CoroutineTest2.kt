package com.lilin.android.retrofittest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 创建日期：2021/9/26 11:26
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * 协程最后一行代码没运行完，线程结束了，所以最后一行没打印
 *
 * 可以看出，线程结束，本线程中的协程同时结束运行
 *
 * 有没有办法保证协程运行完，线程才结束？有的 runBlocking函数
 */
fun main(){
    GlobalScope.launch {
        println("code run in coroutine scope")
        delay(1500)
        //本条日志没打印出来
        println("code run in coroutine scope finished")
    }
    Thread.sleep(1000)
}
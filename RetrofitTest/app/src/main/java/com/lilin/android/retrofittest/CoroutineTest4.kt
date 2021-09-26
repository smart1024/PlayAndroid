package com.lilin.android.retrofittest

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 创建日期：2021/9/26 11:40
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * GlobalScope.launch是顶层协程
 *
 * launch函数必须在协程作用域中创建子协程，子协程在外层协程结束时结束，线程则没有顶层线程这个说法
 *
 * 看日志可见两个子协程是交替输出的，实现了并发
 */
fun main(){
    //runBlocking是函数，参数是lambda表达式
    runBlocking {
        //launch也是函数
        launch {
            println("launch 1")
            delay(1000)
            println("launch 1 finished")
        }

        launch {
            println("launch 2")
            delay(1000)
            println("launch 2 finished")
        }
    }
}
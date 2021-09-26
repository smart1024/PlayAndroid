package com.lilin.android.retrofittest

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 创建日期：2021/9/26 12:29
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */
/**
 * 随着launch函数中越来越复杂，需要抽取为函数
 * coroutineScope可以保证其作用域内的所有代码和子协程在全部执行完之前，外部的协程会一直被挂起
 *
 * 1、创建顶层协程：GlobalScope.launch{}
 * 2、GlobalScope.launch{}和runBlocking{}可以在任意地方调用
 * 3、launch只能在协程作用域中调用
 * 4、coroutineScope函数可以在协程作用域和挂起函数中调用
 */
fun main(){
    runBlocking { //runBlocking创建一个协程，会挂起外部线程（如果在主线程会出现卡死），coroutineScope只会挂起外部协程，不影响外部线程
        //挂起外部协程
        coroutineScope {
            launch {
                for (i in 1..10){
                    println(i)
                    delay(1000)
                }
            }
        }
        println("coroutineScope finished")
    }
    println("runBlocking finished")
}

/**
 * 声明一个挂起函数类似delay()函数，但是无法提供协程作用域因此不能调用launch函数
 * 1、协程作用域才可以调用挂起函数，
 * 2、挂起函数才能调用挂起函数
 */
suspend fun printDot() = coroutineScope{ //coroutineScope协程作用域
    launch { //launch创建子协程
        println(".")
        delay(1000)
    }
}
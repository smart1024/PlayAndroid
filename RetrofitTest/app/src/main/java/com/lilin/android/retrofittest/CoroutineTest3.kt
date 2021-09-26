package com.lilin.android.retrofittest

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 创建日期：2021/9/26 11:33
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */


/**
 * runBlocking保证了协程运行完，线程才结束
 *
 * 需要注意:runBlocking仅在测试环境使用，因为比较费性能
 * 目前程序都运行在单协程中，无法体现其优越性，多协程并发时，能体现出优越性
 */
fun main(){
    runBlocking {
        println("code run in coroutine scope")
        //协程挂起1500ms
        delay(1500)
        println("code run in coroutine scope finished")
    }
}
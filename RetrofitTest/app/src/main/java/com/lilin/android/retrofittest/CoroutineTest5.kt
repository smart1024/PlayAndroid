package com.lilin.android.retrofittest

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 创建日期：2021/9/26 12:19
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * repeat函数循环创建了10万个协程，
 * 100000个协程仅耗时553ms，可见效率之高
 */
fun main(){
    val start = System.currentTimeMillis()
    runBlocking {
        repeat(100000){
            launch {
                println(".")
            }
        }
    }
    val end = System.currentTimeMillis()
    println(end-start)
}
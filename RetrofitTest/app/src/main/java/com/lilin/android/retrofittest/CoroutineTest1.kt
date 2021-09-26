package com.lilin.android.retrofittest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 创建日期：2021/9/26 11:24
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

fun main(){
    GlobalScope.launch {
        println("code run in coroutine scope")
    }
    //发现协程代码打印了
    Thread.sleep(100)
}
package com.lilin.android.retrofittest

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * 创建日期：2021/9/26 11:15
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * Global.launch函数每次创建的都是一个顶层协程，
 * 这种协程当应用程序运行结束时也会跟着一起结束。
 * 刚才的日志之所以无法打印出来，
 * 就是因为代码块中的代码还没来得及运行，
 * 应用程序就结束了
 */
fun main(){

    GlobalScope.launch {
        println("code run in coroutine scope")
    }
}


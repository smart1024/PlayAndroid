package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/15 17:01
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

interface Study {
    fun readBooks()
    fun doHomeWork(){ //接口函数的默认实现
        println("do homework default implementation")
    }
}
package com.lilin.android.kotlin_datastorage

/**
 * 创建日期：2021/9/18 17:45
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 这个build只能用在StringBuilder上
 * fun StringBuilder.build(block:StringBuilder.()):StringBuilder{
 *      block()
 *      return this
 * }
 * 怎么用在任意类上面？泛型
 */
class build {
    //可以在任何类上使用的build，想当与apply函数的功能
    fun <T> T.build(block:T.()->Unit):T{
        block()
        return this
    }
}
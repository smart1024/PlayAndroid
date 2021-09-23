package com.lilin.android.notificationtest

/**
 * 创建日期：2021/9/23 10:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.notificationtest
 * 类说明：
 */

/**
 * 定义了一个String扩展函数，加上infix声明编程infix函数
 */
infix fun String.beginWith(prefix:String) = startsWith(prefix)

/**
 * 定义了一个Collection类的infix扩展函数has
 */
infix fun <T> Collection<T>.has(element: T) = contains(element)

/**
 * 定义A类型的infix扩展函数with，返回值为Pair
 * 实现了跟to()函数相同的逻辑，实现了A with B的语法
 */
infix fun <A,B> A.with(that:B):Pair<A,B> = Pair(this,that)
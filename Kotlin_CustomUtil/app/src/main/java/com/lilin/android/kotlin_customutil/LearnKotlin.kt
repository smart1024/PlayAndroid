package com.lilin.android.kotlin_customutil

import java.lang.RuntimeException
import kotlin.jvm.internal.Intrinsics

/**
 * 创建日期：2021/9/27 10:13
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_customutil
 * 类说明：
 */

/**
 * 编写好用的工具方法
 */
fun main(){
    val max = max(20, 10, 90)
    println(max)
    val max1 = max(1.0,2.0,7.0)
    println(max1)
    val max2 = max(1L,2L,3L)
    println(max2)
}

fun max(vararg nums:Int):Int{
    var max = Int.MIN_VALUE
    for (i in nums){
        max = kotlin.math.max(max,i)
    }
    return max
}

/**
 * Comparable<T>的子类都可以，使用泛型支持能多类型数据比较
 */
fun <T:Comparable<T>> max(vararg nums: T):T{
    if (nums.isEmpty()) throw RuntimeException("params can not be empty")
    var max = nums[0]
    for (num in nums){
        if (num > max){
            max = num
        }
    }
    return max
}
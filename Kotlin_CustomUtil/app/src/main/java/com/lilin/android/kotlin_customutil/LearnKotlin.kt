package com.lilin.android.kotlin_customutil

import kotlin.jvm.internal.Intrinsics

/**
 * 创建日期：2021/9/27 10:13
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_customutil
 * 类说明：
 */
fun main(){
    val max = max(20, 10, 90)
    println(max)
}

fun max(vararg nums:Int):Int{
    var max = Int.MIN_VALUE
    for (i in nums){
        max = kotlin.math.max(max,i)
    }
    return max
}
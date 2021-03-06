package com.lilin.android.kotlin_ui

import java.lang.StringBuilder

/**
 * 创建日期：2021/9/17 16:35
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

/**
 * 定义一个String类的扩展函数
 */
fun String.lettersCount():Int{
    var count = 0
    //this代表字符串本身
    for (char in this){
        if (char.isLetter()){
            count++;
        }
    }
    return count
}

/**
 * String中添加运算符重载扩展函数
 */
//operator fun String.times(n:Int):String{
//    val builder = StringBuilder()
//    repeat(n){
//        builder.append(this)
//    }
//    return builder.toString()
//}

/**
 * Kotlin的String类本身就有repeat函数，因此可以简化下面这样
 */
operator fun String.times(n:Int) = repeat(n)


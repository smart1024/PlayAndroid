package com.lilin.android.kotlin_ui

import java.lang.StringBuilder

/**
 * 创建日期：2021/9/18 09:57
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int,num2: Int):Int{
    return num1+num2
}

fun minus(num1: Int,num2: Int):Int{
    return num1-num2
}

/**
 * StringBuilder类定义了一个build扩展函数，
 * 这个扩展函数接收一个函数类型参数，并且返回值类型也是StringBuilder
 *  类名.语法结构,表示这个函数在哪个类中定义
 *  当我们调用build函数时传入的Lambda表达式将会自动拥有StringBuilder的上下文，
 *  同时这也是apply函数的实现方式
 *  接收一个block函数，block函数参数为StringBuilder类型对象，无返回值，
 *  build函数返回值为StringBuilder类型
 */
fun StringBuilder.build(block: StringBuilder.()->Unit):StringBuilder{
    block()
    return this
}
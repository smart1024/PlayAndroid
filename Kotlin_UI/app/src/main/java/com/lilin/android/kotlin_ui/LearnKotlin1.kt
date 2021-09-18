package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/18 11:32
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

fun main1() {
    println("main start")
    val str =""
    printString(str){
        println("lambda start")
        //局部返回并不执行lambda表达式中后面代码,使用return会报错
        if (str.isEmpty()) return@printString
        println(str)
        println("lambda end")
    }
    println("main end")
}

fun main() {
    println("main start")
    val str =""
    printString1(str){
        println("lambda start")
        //声明为inline是lambda表达式也成为内联函数，内联函数不是真正意义上的函数，这里return的事main函数
        if (str.isEmpty()) return
        println(str)
        println("lambda end")
    }
    println("main end")
}


/**
 main start
printString begin
lambda start
printString end
main end
 不是内联函数的情况下局部返回 lambda return@printString后面代码没有执行，
 因为此时lambda是个真正的函数
 */
fun printString(str: String,block:(String)->Unit){
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun printString1(str: String,block:(String)->Unit){
    println("printString1 begin")
    block(str)
    println("printString1 end")
}
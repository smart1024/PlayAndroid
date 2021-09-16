package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/16 16:16
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 * 函数参数默认值
 *
 * 函数参数默认值很大程度可以替代次构造函数
 *  主构造函数可使用默认值
 */

fun main(){
    printParams(10)
    printParams1(str="world",num = 100)
}

fun printParams(num:Int,str:String = "hello"){
    println("num === $num,str === $str")
}

/**
 * 通过键值对方式传参，可以不用管参数顺序
 */
fun printParams1(num:Int,str:String = "hello"){
    println("num === $num,str === $str")
}
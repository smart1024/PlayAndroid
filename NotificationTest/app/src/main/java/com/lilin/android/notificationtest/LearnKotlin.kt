package com.lilin.android.notificationtest

/**
 * 创建日期：2021/9/23 10:20
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.notificationtest
 * 类说明：
 */

/**
 * if("Hello kotlin".startWith("Hello"))
 * infix函数允许我们将函数调用时的小数点、括号等计算机相关的语法去掉，
 * 从而使用一种更接近英语的语法来编写程序
 * infix函数的两个条件：
 * 1、infix函数不能是顶层函数，必须是某个类的成员函数
 * 2、infix函数只能有一个参数
 */
fun main(){
    infixFun()
    infixFun1()
    infixFun2()
}

fun infixFun2() {
//    val map = mapOf("Apple" to 1,"Banana" to 2,"Orange" to 3,"Pear" to 4,"Grape" to 5)
//    手动实现了to的语法
    val map = mapOf("Apple" with 1,"Banana" with 2,"Orange" with 3,"Pear" with 4,"Grape" with 5)
}

fun infixFun1() {
    val list = listOf("Apple","Banana","Orange","Pear","Grape")
    //if(list.contains("Banana"))
    if(list has "Banana"){
        println("list has \"Banana\" is true")
    }
}

fun infixFun() {
    //使用自定义扩展infix函数,本质调用startWith函数
    if ("Hello kotlin" beginWith "Hello"){
        println("使用自定义 infix 函数 \"Hello kotlin\" beginWith \"Hello\" is true")
    }
}

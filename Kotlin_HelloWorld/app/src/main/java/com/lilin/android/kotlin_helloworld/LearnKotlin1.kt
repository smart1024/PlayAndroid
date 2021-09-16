package com.lilin.android.kotlin_helloworld

import java.util.*

/**
 * 创建日期：2021/9/16 13:55
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

fun main(){

    //实现了求名字最长的水果
    val list = listOf("Apple","Orange","Banana","Watermelon")
    var maxLenFruit = ""
    for (fruit in list){
        if (fruit.length > maxLenFruit.length){
            maxLenFruit = fruit
        }
    }
    println("max length is $maxLenFruit")


    //优化写法（maxBy @DeprecatedSinceKotlin(warningSince = "1.4", errorSince = "1.5")）
    val maxLengthFruit1 = list.maxByOrNull { it.length }
    println("maxLengthFruit1 is $maxLengthFruit1")

    //maxByOrNull实际上接收的是lambda表达式
    val lambda = {fruit:String -> fruit.length}

    val maxLenFruit2 = list.maxByOrNull(lambda)
    println("maxLenFruit2 is $maxLenFruit2")

    //简化代码
    val maxLenFruit3 = list.maxByOrNull({fruit:String->fruit.length})
    println("maxLenFruit3 is $maxLenFruit3")

    //继续简化 当Lambda参数是函数的最后一个参数时，可以将Lambda表达式移到函数括号的外面
    val maxLenFruit4 = list.maxByOrNull(){fruit:String->fruit.length}
    println("maxLenFruit4 is $maxLenFruit4")

    //继续简化 Lambda参数是函数的唯一一个参数的话，可以省略()
    val maxLenFruit5 = list.maxByOrNull{fruit:String->fruit.length}
    println("maxLenFruit5 is $maxLenFruit5")

    //继续简化 由于Kotlin拥有出色的类型推导机制，Lambda表达式中的参数列表其实在大多数情况下不必声明参数类型
    val maxLenFruit6 = list.maxByOrNull{fruit->fruit.length}
    println("maxLenFruit6 is $maxLenFruit6")

    //继续简化 当Lambda表达式的参数列表中只有一个参数时，也不必声明参数名，而是可以使用it关键字来代替
    val maxLenFruit7 = list.maxByOrNull{it.length}
    println("maxLenFruit7 is $maxLenFruit7")

    //将原始值映射成新的值
    val newList = list.map { it.uppercase() }
    for (fruit in newList){
        println(fruit)
    }

    //filter组合map使用
    val newList1 = list.filter { it.length <= 5 }.map { it.uppercase() }
    for (fruit in newList1){
        println(fruit)
    }
}

/**
 *  8、
 *  1）Lambda就是一小段可以作为参数传递的代码
 *  lambda结构 {参数名1:参数类型,参数名2:参数类型 -> 代码}
 *  最后一行自动为lambda表达式的值
 *  val lambda = {fruit:String -> fruit.length}
 *
 *  2）集合中比较常用的函数式API（经常使用lambda表达式）
 *  1>map它用于将集合中的每个元素都映射成一个另外的值，映射的规则在Lambda表达式中指定，最终生成一个新的集合
 *  2>filter函数是用来过滤集合中的数据的，它可以单独使用，也可以配合刚才的map函数一起使用
 */
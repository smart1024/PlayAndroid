package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 15:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

/**
 * 1、Kotlin泛型实化：T泛型为具体类，java中没这个概念
 * 所有基于JVM的语言，它们的泛型功能都是通过类型擦除机制来实现的
 * Kotlin中是可以将内联函数中的泛型进行实化的
 * 1、是内联函数
 * 2、reified关键字修饰泛型
 * 来表示泛型要进行实化
 * inline fun <reified T> getGenericType(){}
 */
fun main(){

    val genericType1 = getGenericType<String>()
    val genericType2 = getGenericType<Int>()
    println("genericType1 is $genericType1")
    println("genericType2 is $genericType2")
}

/**
 * 泛型实化可以获取泛型的具体类型
 */
inline fun <reified T> getGenericType() = T::class.java
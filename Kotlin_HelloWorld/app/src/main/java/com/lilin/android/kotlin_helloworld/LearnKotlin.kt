package com.lilin.android.kotlin_helloworld

import kotlin.math.max

/**
 * 创建日期：2021/9/14 16:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：在Android studio中独立运行kotlin代码
 * new 选择File通常是用于编写Kotlin顶层函数和扩展函数的
 * git token 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
 */

/**
 * 1、Kotlin变量的定义
 */
fun main(){
    println("Hello Kotlin")

    /**
     * 定义final变量
     * kotlin类型推导机制
     */
    val a = 10
    println("a= "+a)

    val b = 37
    val c = 40
    val value = largeNumber(b,c)

    //表示一个区间[0,10]代表双端闭区间
    val range = 0..10

    //for循环
    for (i in range){
        println(i)
    }

    //表示一个[0,10)
    val range1 = 0 until 10
    //forin默认递增1，step可指定递增大小
    for (i in range1 step 2){
        println(i)
    }

    //downTo创建一个降序区间
    for (i in 10 downTo 1){
        println(i)
    }

    //字符串模板
    println("large Number is $value")

    checkNumber(10)


    /**
     * 3、对象的定义
     */
    val p = Person()
    p.name = "Jack"
    p.age = 19;
    p.eat()
}

/**
 * 2、Kotlin函数的定义
 */
/**当函数中只有一行代码，可以直接把这行代码用等号赋值给函数定义*/
//fun largeNumber(num1:Int,num2: Int):Int{
//    return max(num1,num2)
//}

//fun largeNumber(num1: Int,num2: Int):Int = max(num1,num2)

/**kotlin的类型推导：max函数返回Int型，因此可省略largeNumber的返回值类型*/
fun largeNumber(num1:Int,num2:Int) = max(num1,num2)

/**
 * 2、Kotlin的if语句
 */
/**Kotlin的if语句会有返回值，返回值为if语句每个条件的最后一行代码的返回值*/
//fun myLargeNumber(num1: Int,num2: Int):Int{
//    var value = 0;
//    if (num1 > num2){
//        value = num1
//    }else{
//        value = num2
//    }
//    return value
//}

//fun myLargeNumber(num1: Int,num2: Int):Int{
//    var value = if (num1 > num2){
//        num1
//    }else{
//        num2
//    }
//    return value
//}

/**省略return语句*/
//fun myLargeNumber(num1: Int,num2: Int):Int{
//    return if (num1>num2){
//        num1
//    }else{
//        num2
//    }
//}

/**省略{}*/
//fun myLargeNumber(num1: Int,num2: Int) = if (num1 > num2){
//    num1
//}else{
//    num2
//}

fun myLargeNumber(num1: Int,num2: Int)= if (num1>num2) num1 else num2

/**
 *  3、Kotlin的when语句
 */

//使用if实现，判断条件非常多
//fun getScore(name:String) = if (name == "Tom"){
//    80
//}else if (name == "Jim"){
//    77
//}else if (name == "Jack"){
//    95
//} else if (name == "Lily"){
//    100
//}else{
//    0
//}

//使用when精准匹配 类似java中的switch
//fun getScore(name:String) = when(name){
//    "Tom" -> 80
//    "Jim" -> 77
//    "jack"-> 95
//    "Lily"-> 100
//    else -> 0
//}

//when语句中不传入参数name
//fun getScore(name:String) = when{
//    name == "Tom" -> 80
//    name == "Jim" -> 77
//    name == "jack"-> 95
//    name == "Lily"->100
//    else -> 0
//}

//when实现以Tom开头的都是86分
fun getScore(name:String) = when{
    name.startsWith("Tome") -> 86
    name == "Jim" -> 77
    name == "jack"-> 95
    name == "Lily"->100
    else -> 0
}

//使用when和is关键字实现类型匹配
fun checkNumber(num:Number){
    when(num){
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}


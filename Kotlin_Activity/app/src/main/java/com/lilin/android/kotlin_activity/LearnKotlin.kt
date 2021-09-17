package com.lilin.android.kotlin_activity

import java.lang.StringBuilder

/**
 * 创建日期：2021/9/17 09:05
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_activity
 * 类说明：
1、标准函数和静态方法
标准函数：Standard.kt中定义的函数
with、run、apply
之前学过let函数，作用是配合?.辅助判空

1> with函数接收两个参数：1、一个任意类型的上下文对象 2、一个参数lambda表达式，返回值为lambda表达式最后一行代码的返回值
with函数的作用：精简多次使用上下对象的场景 eatFruit1用法，返回值toString()的返回值
2> run函数只接受一个lambda表达式作为参数并且只能在某个作为上下文对象上使用，其他特点同with函数
3> apply函数和run函数类似，只接受一个lambda参数，都要在作为上下文的某类型对象上使用，单apply函数无法指定返回值，返回值为对象自己

 2、静态方法
java方式
 public class Util{
    public static void doAction(){
        System.out.println("do action");
    }
}

 kotlin方式实现：通过单例类
//kotlin这里不是静态方法，但是可以实现相同效果
object Util{
    fun doAction(){
        println("do action")
    }
}

 object 方式会将对象内部所有方法变成类似静态方法的调用方式
  如果想某个方法这样该如何解决
class Util{
    fun doAction(){
        println("do action")
    }

    companion object{ //联合对象,伴生类
        fun doAction1(){
            println("do action1")
        }
    }
}

 当然以上两种Kotlin语法特性，能形式上实现java的静态方法调用效果，但并不是真正的静态方法，那有没有办法实现静态？

 有的
 注解和顶层方法
1》@JvmStatic注解
 只能用在object单例类的方法上和伴生类的方法上
2》 顶层方法:方法没有定义在类中，比如main方法，Kotlin编译器会将所有顶层方法编译为静态方法
 Helper文件定义了一个顶层方法，可以直接在Kotlin代码中任意地方直接调用，但不能在java中直接使用，java中没这个概念，
 可以通过Helper.doSomething()这种方式使用
 *
 */

fun main(){
    val list = listOf("Apple","Banana","Orange","Pear","Grape")

    eatFruit(list)
    eatFruit1(list)
    eatFruit2(list)
    eatFruit3(list)

    Util.doAction1()
}

/**
 * apply函数在某个上下文对象上的使用
 * apply返回值为该对象自身
 */
fun eatFruit3(list: List<String>) {
    val result = StringBuilder().apply {
        append("eatFruit3 Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("eat all fruits.")
    }
    println(result.toString())
    println("========================")
}

/**
 * run函数在某个上下文对象中使用
 */
fun eatFruit2(list: List<String>) {
    val result = StringBuilder().run{
        append("eatFruit2 Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("eat all fruits.")
        toString()
    }
    println(result)
    println("========================")
}

/**
 * 使用with起到精简多次使用上下文的场景
 */
fun eatFruit1(list: List<String>) {
    val result = with(StringBuilder()){
        append("eatFruit1 Start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("eat all fruits.")
        toString()
    }
    println(result)
    println("========================")
}

/**
 * 连续调用了很多次builder对象的方法
 */
fun eatFruit(list: List<String>) {
    val builder = StringBuilder()
    builder.append("eatFruit Start eating fruits.\n")
    for (fruit in list){
        builder.append(fruit).append("\n")
    }
    builder.append("eat all fruits.")
    val result = builder.toString()
    println(result)
    println("========================")
}



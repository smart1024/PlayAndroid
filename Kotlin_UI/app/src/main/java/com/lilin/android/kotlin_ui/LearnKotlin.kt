package com.lilin.android.kotlin_ui

import java.lang.IllegalArgumentException

/**
 * 创建日期：2021/9/17 15:53
 * @author lilin
 * @version 1.0
 * 包名：
 * 类说明：
 */

fun main(){

}

/**
 * Result的执行结果只可能是Success或者Failure,else分支不可能走到，但必须满足语法检查而已
 * 这种为了满足编译器的要求而编写无用条件分支的情况不仅在Kotlin当中存在，
 *
 * 编写else条件还有一个潜在的风险。如果我们现在新增了一个Unknown类并实现Result接口，用于表示未知的执行结果，
 * 但是忘记在getResultMsg()方法中添加相应的条件分支，编译器在这种情况下是不会提醒我们的，
 * 而是会在运行的时候进入else条件里面，从而抛出异常并导致程序崩溃
 *
 * 在Java或者是其他编程语言当中也普遍存在
 * 为了解决这个问题 Kotlin设计了sealed class 密封类
 */
fun getResultMsg(result: Result) = when(result){
    is Success -> result.msg
    is Failure -> result.err
    else -> throw IllegalArgumentException()
}

/**
 * 去掉else条件仍然能编译通过呢？
 * 这是因为当在when语句中传入一个密封类变量作为条件时，
 * Kotlin编译器会自动检查该密封类有哪些子类，
 * 并强制要求你将每一个子类所对应的条件全部处理
 */
fun getResultMsg1(result: Result1) = when(result){
    is Success1 -> result.msg
    is Failure1 -> result.err
}
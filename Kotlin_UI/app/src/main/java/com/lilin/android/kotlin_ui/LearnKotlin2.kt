package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/18 14:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

fun main(){
    runRunnable {
        println("========")
// 使用crossinline后函数参数中(等价lambda表达式)，不能直接使用return了，只能使用return@runRunnable
//        return@runRunnable
    }
}

/**
 * 创建了Runnable匿名类实现，并在其中使用函数参数调用(block()),因为block中可能包含return语句
 * 内联函数中 函数参数中允许直接return外层函数，当函数参数中包含return语句时，用在匿名对象中使用会报错，
 * 因为作用域的问题，不能return至外层函数，怎么解决
 * 内联函数的Lambda表达式中允许使用return关键字，
 * 和高阶函数的匿名类实现中不允许使用return关键字之间造成了冲突
 *
 使用crossinline声明的函数参数中(等价lambda表达式)，不能直接使用return了，只能使用return@runRunnable
 */
inline fun runRunnable1(crossinline block: () -> Unit){

    val runnable = Runnable(){ //匿名类实现中不允许直接使用return，但可使用return@Runnable
        block()
    }
    runnable.run()
}

/**
 * 没加inline能正常工作
 */
fun runRunnable(block:()->Unit){
    val runnable = Runnable {
        block()
    }
    runnable.run()
}
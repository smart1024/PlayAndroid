package com.lilin.android.kotlin_datastorage

import kotlin.reflect.KProperty

/**
 * 创建日期：2021/9/22 15:29
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * Later类，并将它指定成泛型类。Later的构造函数中接收一个函数类型参数，这个函数类型参数不接收任何参数
 * 并且返回值就是Later类型指定的泛型
 */
class Later<T>(val block:()->T) {
    var value:Any? = null

    /**
     * 第一个参数：Any类型，说明支持所有类
     * 懒加载技术
     * 懒加载技术是不会对属性进行赋值
     */
    operator fun getValue(any: Any?,prop:KProperty<*>):T{
        if (value == null){
            value = block()
        }
        return value as T
    }
}

/**
 * 不过为了让它的用法更加类似于lazy函数，最好再定义一个顶层函数
 * 创建Later类的实例，并将接收的函数类型参数传给Later类的构造函数
 */
fun <T> later(block: () -> T) = Later(block)
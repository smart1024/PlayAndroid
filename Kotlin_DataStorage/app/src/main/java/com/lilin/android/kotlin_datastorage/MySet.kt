package com.lilin.android.kotlin_datastorage

/**
 * 创建日期：2021/9/22 14:15
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 类的委托通过by关键字：解决继承自接口中众多抽象方法要实现的问题，这样可以仅实现自己特有方法，或者增强已有方法
 * 类委托的核心思想是将一个类的具体实现委托给另一个类去完成，
 * 而委托属性的核心思想是将一个属性（字段）的具体实现委托给另一个类去完成
 */
class MySet<T>(private val helperSet: HashSet<T>):Set<T> by helperSet {
    fun helloWorld() = print("Hello world")
    override fun isEmpty() = false
}
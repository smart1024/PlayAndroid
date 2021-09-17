package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/17 16:03
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

/**
 * 密封类及其所有子类只能定义在同一个文件的顶层位置，
 * 不能嵌套在其他类中，这是被密封类底层的实现机制所限制的
 */
sealed class Result1

class Success1(val msg:String) : Result1()

class Failure1(val err:String) : Result1()
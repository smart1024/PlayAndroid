package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/15 16:57
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

/**
 * 集成父类不需要加()的情形
 * Student类的后面没有显式地定义主构造函数，同时又因为定义了次构造函数，
 * 所以现在Student类是没有主构造函数的。那么既然没有主构造函数，
 * 继承Person类的时候也就不需要再加上括号了
 */
class Student1:Person {
    constructor(name:String,age:Int):super(name,age)
}
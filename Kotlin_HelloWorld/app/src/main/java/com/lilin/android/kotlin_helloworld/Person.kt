package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/14 17:48
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

open class Person(val name:String,val age:Int) {
//    var name = ""
//    var age = 0
    constructor():this("",0)
    fun eat(){
        println("$name is eating,he is $age years old")
    }
}
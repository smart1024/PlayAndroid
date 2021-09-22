package com.lilin.android.kotlin_datastorage

import kotlin.reflect.KProperty

/**
 * 创建日期：2021/9/22 14:31
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 一种标准的代码实现模板，
 * 在Delegate类中我们必须实现getValue()和setValue()这两个方法，
 * 并且都要使用operator关键字进行声明
 */
class Delegate {
    var propValue:Any? = null

    /**
     * 第一个参数：声明Delegate可以用在什么类中委托功能中
     * 第二个参数：KProperty<*>是Kotlin中的属性操作类，可用于获取各种属性相关的值
     * <*>这种泛型的写法表示你不知道或者不关心泛型的具体类型，只是为了通过语法编译而已
     */
    operator fun getValue(myClass: MyClass,prop:KProperty<*>):Any?{
        return propValue
    }

    operator fun setValue(myClass: MyClass,prop: KProperty<*>,value:Any?){
        propValue = value
    }
}
package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/17 16:43
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 * 关键字operator和函数名plus都是固定不变的
 */

class Money(val value: Int) {
    /**
     * Money与Money类相加
     */
    operator fun plus(money:Money):Money{
        val sum = value + money.value
        return Money(sum)
    }

    /**
     * Money与一个整形数字相加
     */
    operator fun plus(newValue:Int):Money{
        val sum = value + newValue
        return Money(sum)
    }
}
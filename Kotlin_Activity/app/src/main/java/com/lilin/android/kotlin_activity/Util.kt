package com.lilin.android.kotlin_activity

/**
 * 创建日期：2021/9/17 09:52
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_activity
 * 类说明：
 */

class Util {
    fun doAction(){
        println("do action")
    }
    companion object{
        /**
         * @JvmStatic 真正实现了把方法转为静态方法
         */
        @JvmStatic
        fun doAction1(){
            println("do action1")
        }
    }
}
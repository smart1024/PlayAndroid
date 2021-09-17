package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/17 16:26
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

/**
 * 不使用扩展函数统计字符串中字母的个数的写法
 */
object StringUtil {
    fun lettersCount(str: String):Int{
        var count = 0
        for (char in str){
            if (char.isLetter()){
                count++;
            }
        }
        return count
    }
}
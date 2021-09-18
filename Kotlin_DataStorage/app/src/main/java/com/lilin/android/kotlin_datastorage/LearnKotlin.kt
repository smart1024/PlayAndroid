package com.lilin.android.kotlin_datastorage

import androidx.core.content.contentValuesOf

/**
 * 创建日期：2021/9/18 16:53
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

class LearnKotlin {
    fun main(){
//        自定义了类似mapof()的结构
        val values = cvof1("name" to "Game of Thrones","author" to "George Martin","pages" to 720,"price" to 20.85)
        //实际上ktx核心库提供了此方法
        val values1 = contentValuesOf("name" to "Game of Thrones","author" to "George Martin","pages" to 720,"price" to 20.85)
    }
}
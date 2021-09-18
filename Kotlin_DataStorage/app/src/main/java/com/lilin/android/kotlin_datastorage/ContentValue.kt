package com.lilin.android.kotlin_datastorage

import android.content.ContentValues

/**
 * 创建日期：2021/9/18 16:45
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 1、声明vararg 可变参数
 * 2、Any是Kotlin中所有类的共同基类，相当于Java中的Object，而Any?则表示允许传入空值
 * 3、apply函数进行优化
 */

fun cvof1(vararg pairs: Pair<String,Any?>):ContentValues = ContentValues().apply {
    for (pair in pairs){
        val key = pair.first
        val value = pair.second
        when(value){
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Short -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is Boolean -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            is ByteArray -> put(key, value)
            null -> putNull(key)
        }
    }
}

fun cvof(vararg pairs: Pair<String,Any?>):ContentValues{
    val cv = ContentValues()
    for (pair in pairs){
        val key = pair.first
        val value = pair.second
        when(value){
            is Int -> cv.put(key, value)
            is Long -> cv.put(key, value)
            is Short -> cv.put(key, value)
            is Float -> cv.put(key, value)
            is Double -> cv.put(key, value)
            is Boolean -> cv.put(key, value)
            is String -> cv.put(key, value)
            is Byte -> cv.put(key, value)
            is ByteArray -> cv.put(key, value)
            null -> cv.putNull(key)
        }
    }
    return cv
}

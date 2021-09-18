package com.lilin.android.kotlin_datastorage

import android.content.SharedPreferences

/**
 * 创建日期：2021/9/18 15:57
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 给SharedPreferences添加扩展方法
 */
fun SharedPreferences.open(block:SharedPreferences.Editor.()->Unit){
    val edit = edit()
    edit.block()
    edit.apply()
}


package com.lilin.android.kotlin_customutil

import android.content.Context
import android.widget.Toast

/**
 * 创建日期：2021/9/27 10:52
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_customutil
 * 类说明：
 */

fun String.showToast(context: Context,duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}

fun Int.showToast(context: Context,duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}
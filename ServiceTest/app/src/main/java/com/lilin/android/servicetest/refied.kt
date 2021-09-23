package com.lilin.android.servicetest

import android.content.Context
import android.content.Intent

/**
 * 创建日期：2021/9/23 15:28
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

/**
 * 由于泛型T已经实化为具体类
 */
inline fun <reified T> startActivity(context: Context){
    val intent = Intent(context,T::class.java)
    context.startActivity(intent)
}
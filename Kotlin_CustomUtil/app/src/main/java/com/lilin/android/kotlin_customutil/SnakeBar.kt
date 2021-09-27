package com.lilin.android.kotlin_customutil

import android.text.TextUtils
import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * 创建日期：2021/9/27 11:00
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_customutil
 * 类说明：
 */

/**
 * 没有实现setAction
 */
fun View.showSnakeBar(text:String,duration:Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(this,text,duration).show()
}

fun View.showSnakeBar(resId:Int,duration: Int = Snackbar.LENGTH_SHORT){
    Snackbar.make(this,resId,duration).show()
}

fun View.showSnakeBar(text: String,actionText:String? = null ,duration: Int = Snackbar.LENGTH_SHORT,block:(()->Unit)? = null){
    val snackBar = Snackbar.make(this,text,duration)
    if (actionText!=null && block != null){
        snackBar.setAction(actionText){
            block()
        }
    }
    snackBar.show()
}

fun View.showSnakeBar(resId: Int,actionResId: Int?=null,duration: Int = Snackbar.LENGTH_SHORT,block: (() -> Unit)?= null){
    val snackBar = Snackbar.make(this,resId,duration)
    if (actionResId != null && block !=null){
        snackBar.setAction(actionResId){
            block()
        }
    }
    snackBar.show()
}
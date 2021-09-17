package com.lilin.android.kotlin_ui

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.lilin.android.kotlin_ui.databinding.TitleBinding

/**
 * 创建日期：2021/9/17 10:50
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

class TitleLayout(context: Context,attrs:AttributeSet) : LinearLayout(context, attrs) {
    init {
        //方法1，加载布局
//        val view = LayoutInflater.from(context).inflate(R.layout.title,this)
//        val bind = TitleBinding.bind(view)
//        bind.titleBack.setOnClickListener {
//            val activity = context as Activity
//            activity.finish()
//        }
        //方法2
        val titleBinding = TitleBinding.inflate(LayoutInflater.from(context), this, true)

        titleBinding.titleBack.setOnClickListener {
            //类型转换
            val activity = context as Activity
            activity.finish()
        }

        titleBinding.titleEdit.setOnClickListener {
            Toast.makeText(context,"You clicked edit button",Toast.LENGTH_SHORT).show()
        }
    }
}
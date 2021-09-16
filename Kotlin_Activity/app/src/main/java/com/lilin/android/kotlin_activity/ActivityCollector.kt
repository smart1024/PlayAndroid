package com.lilin.android.kotlin_activity

import android.app.Activity

/**
 * 创建日期：2021/9/16 17:49
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_activity
 * 类说明：
 */

object ActivityCollector {
    private val activities = ArrayList<Activity>()
    fun addActivity(activity: Activity){
        activities.add(activity)
    }
    fun removeActivity(activity: Activity){
        activities.remove(activity);
    }

    fun finishAll(){
        for (activity in activities){
            if (!activity.isFinishing){ //判断Activity是否正在销毁
                activity.finish()
            }
        }
        activities.clear()
    }
}
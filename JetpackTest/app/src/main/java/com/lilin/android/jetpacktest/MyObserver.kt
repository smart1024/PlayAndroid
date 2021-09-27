package com.lilin.android.jetpacktest

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 创建日期：2021/9/27 15:50
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * 想要感知到Activity的生命周期，
 * 还得借助额外的注解功能才行
 */
class MyObserver:LifecycleObserver {
    companion object{
        const val TAG = "MyObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart(){ //与onStart对应
        Log.e(TAG,"activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop(){ //onStop对应
        Log.e(TAG,"activityStop")
    }
}
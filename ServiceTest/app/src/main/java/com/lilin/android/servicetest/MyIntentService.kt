package com.lilin.android.servicetest

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * 创建日期：2021/9/23 14:39
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

/**
 * IntentService会开启子线程执行耗时逻辑，任务执行完毕，会自动停止
 */
class MyIntentService:IntentService("MyIntentService") {
    companion object{
        const val TAG = "MyIntentService"
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG,"Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy executed")
    }
}
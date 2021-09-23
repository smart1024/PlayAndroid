package com.lilin.android.servicetest

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 * 创建日期：2021/9/23 14:54
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

/**
 * JobIntent Service内部是AsyncTask处理任务，基本上同IntentService
 * 已经过期
 */
class MyJobIntentService:JobIntentService() {
    companion object{
        const val TAG = "MyJobIntentService"
    }
    override fun onHandleWork(intent: Intent) {
        val name = intent.getStringExtra("name")
        Log.e(TAG,"name is $name Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy executed")
    }
}
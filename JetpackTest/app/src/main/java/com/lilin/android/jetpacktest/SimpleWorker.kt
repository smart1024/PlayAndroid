package com.lilin.android.jetpacktest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 创建日期：2021/9/28 13:54
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

class SimpleWorker(context: Context,params: WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        Log.e("SimpleWorker",Thread.currentThread().name)
        Log.e("SimpleWorker","do work in SimpleWorker")
        return Result.success() //表示任务运行结果失败Result.failure()/Result.retry()
    }
}
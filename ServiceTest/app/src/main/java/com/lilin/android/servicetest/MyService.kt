package com.lilin.android.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * Android8.0以后，后台运行的Service随时可能被系统回收，
 * 前台service可以保持一致运行，因为状态栏可见
 */
class MyService : Service() {
    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder(){
        fun startDownload(){
            Log.e(TAG,"startDownload executed")
        }

        fun getProgress():Int{
            Log.e(TAG,"getProgress executed")
            return 0
        }
    }

    companion object{
        const val TAG = "MyService"
    }
    override fun onCreate() { //service第一次创建才会执行
        super.onCreate()
        Log.e(TAG,"onCreate")
    }

    //onBind实现与Activity的通信，startBind后才会被调用
    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    //Service启动时调用
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int { //每次开启都会调用
        Log.e(TAG,"onStartCommand")
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"onDestroy")

    }
}
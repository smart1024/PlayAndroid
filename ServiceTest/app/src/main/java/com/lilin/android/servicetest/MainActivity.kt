package com.lilin.android.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import androidx.core.app.JobIntentService
import com.lilin.android.servicetest.databinding.ActivityMainBinding


/**
 * 不知道你有没有发现，虽然Service是在Activity里启动的，
 * 但是在启动了Service之后，Activity与Service基本就没有什么关系了
 * onBind方法是通信的桥梁
 * 实现了Service和Activity的绑定
 *
 * Service都是运行在主线程中，不能处理耗时逻辑
 */
class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var downloadBinder: MyService.DownloadBinder

    //匿名对象
    private val connection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        inflate.startService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            startService(intent)
        }

        inflate.stopService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            stopService(intent)
        }

        inflate.bindService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE) //绑定service
        }

        inflate.unBindService.setOnClickListener {
            unbindService(connection)
        }

        inflate.startForgroundService.setOnClickListener {
            val intent = Intent(this,ForegroundService::class.java)
            startService(intent)
        }

        inflate.startIntentService.setOnClickListener {
            Log.e(TAG,"Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this,MyIntentService::class.java)
            startService(intent)
        }
        inflate.startJobIntentService.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //android 8.0以后
                val jobId = 8
                val intent = Intent()
                intent.putExtra("name","ZhangSan")
                JobIntentService.enqueueWork(this,MyJobIntentService::class.java,jobId,intent)
            }
        }
    }
}
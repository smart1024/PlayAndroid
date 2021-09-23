package com.lilin.android.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import com.lilin.android.servicetest.databinding.ActivityMainBinding


/**
 * 不知道你有没有发现，虽然Service是在Activity里启动的，
 * 但是在启动了Service之后，Activity与Service基本就没有什么关系了
 * onBind方法是通信的桥梁
 * 实现了Service和Activity的绑定
 */
class MainActivity : AppCompatActivity() {
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
    }
}
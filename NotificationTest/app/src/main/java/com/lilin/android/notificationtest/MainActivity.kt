package com.lilin.android.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.NotificationCompat
import com.lilin.android.notificationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //android 8.0以上
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        inflate.noticeTest.setOnClickListener {
            val intent = Intent(this,NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this,0,intent,0)
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.icon_launcher))
                .setContentIntent(pi)
//                .setAutoCancel(true) //1、让通知点击自动消失
                .build()
            manager.notify(1,notification)
        }
    }
}
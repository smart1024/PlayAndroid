package com.lilin.android.servicetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

/**
 * 前台service
 */
class ForegroundService : Service() {
    companion object{
        const val TAG = "ForegroundService"
    }
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG,"onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("ForegroundService","前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this,MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent,0)
        val notification = NotificationCompat.Builder(this,"ForegroundService")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.mipmap.icon_launcher))
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()
        startForeground(1,notification)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
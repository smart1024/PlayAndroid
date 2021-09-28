package com.lilin.android.jetpacktest

import android.net.wifi.WpsInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.lilin.android.jetpacktest.databinding.ActivityWorkManagerBinding
import java.util.concurrent.TimeUnit

/**
 * WorkManager的所有功能，在国产手机上都有可能得不到正确的运行。
 * 这是因为绝大多数的国产手机厂商在进行Android系统定制的时候会增加一个一键关闭的功能，
 * 允许用户一键杀死所有非白名单的应用程序。
 * 而被杀死的应用程序既无法接收广播，
 * 也无法运行WorkManager的后台任务
 */
class WorkManagerActivity : AppCompatActivity() {
    private val request: OneTimeWorkRequest by lazy {
        //单次任务OneTimeWorkRequest，WorkRequest.Builder的子类
        OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInitialDelay(5,TimeUnit.SECONDS)
            .addTag("simple") //添加标签可以用来取消此任务
            .build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityWorkManagerBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.oneTimeWorkBtn.setOnClickListener { //几乎是在子线程中立即执行

            WorkManager.getInstance(this).enqueue(request)
        }

        inflate.cancelOneTimeWorkBtn.setOnClickListener {
//            WorkManager.getInstance(this).cancelAllWorkByTag("simple")
            WorkManager.getInstance(this).cancelWorkById(request.id)
//            WorkManager.getInstance(this).cancelAllWork() 取消所有的后台任务
        }

        inflate.periodicTimeWorkBtn.setOnClickListener { //周期性任务时间间隔不少于15分钟
            //构建周期性运行的后台任务请求
            val request = PeriodicWorkRequest.Builder(SimpleWorker::class.java,15,TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(request.id) //返回LiveData,监听后台任务执行结果
            .observe(this){ workInfo->
                if (workInfo.state == WorkInfo.State.SUCCEEDED){
                    Log.e("WorkManagerActivity","do work success")
                }else if(workInfo.state==WorkInfo.State.FAILED){
                    Log.e("WorkManagerActivity","do work failed")
                }
        }

//        链式任务 同步sync任务、压缩compress、上传upload
//        WorkManager.getInstance(this).beginWith(sync).then(compress).then(upload).enqueue()
    }
}
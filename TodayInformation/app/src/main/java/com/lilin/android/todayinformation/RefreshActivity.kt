package com.lilin.android.todayinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import com.lilin.android.todayinformation.databinding.ActivityRefreshBinding
import com.lilin.android.todayinformation.refresh.MeiTuanRefreshHeaderManager

class RefreshActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityRefreshBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        inflate.myRefreshLayout.setRefreshManager(MeiTuanRefreshHeaderManager(this))
        inflate.myRefreshLayout.setOnRefreshListener {
            Log.e("RefreshActivity","执行了刷新动作")
            Handler().postDelayed({
                inflate.myRefreshLayout.finishRefresh()
            },2000)
        }
    }
}
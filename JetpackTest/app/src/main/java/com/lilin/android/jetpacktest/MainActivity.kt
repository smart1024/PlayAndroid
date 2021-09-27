package com.lilin.android.jetpacktest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.lilin.android.jetpacktest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var inflate: ActivityMainBinding
    lateinit var sp:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        sp = getPreferences(Context.MODE_PRIVATE)

        val counterReserved = sp.getInt("counterReserved",0)
        //们绝对不可以直接去创建ViewModel的实例，而是一定要通过ViewModelProvider来获取ViewModel的实例,
        // 否则每次onCreate都会创建
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel = ViewModelProvider(this,MainViewModelFactory(counterReserved))[MainViewModel::class.java]

        inflate.plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }
        inflate.clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }

        refreshCounter()

        inflate.lifecycle.setOnClickListener {
            startActivity(Intent(this,LifecycleActivity::class.java))
        }

        inflate.liveData.setOnClickListener {
            startActivity(Intent(this,LiveDataActivity::class.java))
        }
    }

    private fun refreshCounter() {
        inflate.infoText.text = "${viewModel.counter}"
    }

    override fun onPause() {
        super.onPause()
        sp.edit { //使用的Kotlin的扩展函数
            putInt("counterReserved",viewModel.counter)
        }
    }
}

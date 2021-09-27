package com.lilin.android.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.lilin.android.jetpacktest.databinding.ActivityLifecycleBinding

/**
 *  Activity是继承自AppCompatActivity的，
 *  或者你的Fragment是继承自androidx.fragment.app.Fragment的，
 *  那么它们本身就是一个LifecycleOwner的实例
 *  ComponentActivity看源码可见实现了LifecycleOwner接口
 */
class LifecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityLifecycleBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        lifecycle.addObserver(MyObserver())
    }
}
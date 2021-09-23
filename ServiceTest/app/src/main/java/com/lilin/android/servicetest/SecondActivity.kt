package com.lilin.android.servicetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.lilin.android.servicetest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivitySecondBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        inflate.tv.text = "通过泛型实化方法跳转到本界面 $name $age"
    }
}
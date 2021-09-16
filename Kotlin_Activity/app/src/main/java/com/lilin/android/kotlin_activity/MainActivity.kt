package com.lilin.android.kotlin_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lilin.android.kotlin_activity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(layoutInflater)
        setContentView(inflate.root)

        //xml tools:viewBindingIgnore="true" 可以禁止viewbinding生成
        inflate.button1.setOnClickListener {
            Toast.makeText(this,"You clicked button1",Toast.LENGTH_SHORT).show()
        }
    }
}
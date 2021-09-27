package com.lilin.android.kotlin_customutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.lilin.android.kotlin_customutil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        //简化Toast写法
        inflate.btn1.setOnClickListener {
            "This is a toast".showToast(this)
            R.string.app_name.showToast(this)
        }

        inflate.btn2.setOnClickListener {
            inflate.btn2.showSnakeBar("Snakebar show")
        }
        inflate.btn3.setOnClickListener { //接收函数作为参数，可以写成lambda表达式的形式传入
            inflate.btn3.showSnakeBar("Snakebar show","Action text") {
                Log.e(TAG, "this is snakeBar log")
            }
        }

        inflate.btn4.setOnClickListener { //接收函数作为参数，可以写成lambda表达式的形式传入
            inflate.btn4.showSnakeBar(R.string.app_name,R.string.app_name) {
                Log.e(TAG, "this is snakeBar log")
            }
        }
    }
}
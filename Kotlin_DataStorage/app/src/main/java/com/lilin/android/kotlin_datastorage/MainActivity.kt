package com.lilin.android.kotlin_datastorage

import android.content.Intent
import android.content.UriMatcher
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.lilin.android.kotlin_datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG:String = "MainActivity"
    val urlMatcher by later {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher
    }
    val p by later { //自定义懒加载
        Log.e(TAG,"p MainActivity")
        "test later"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.button1.setOnClickListener {
            startActivity(Intent(this,SharePreferenceActivity::class.java))
        }

        inflate.button2.setOnClickListener {
            p
            Toast.makeText(this,"button3 clicked",Toast.LENGTH_SHORT).show()
        }
        inflate.button3.setOnClickListener {
            Toast.makeText(this,"button3 clicked",Toast.LENGTH_SHORT).show()
        }
        inflate.button4.setOnClickListener {
            Toast.makeText(this,"button4 clicked",Toast.LENGTH_SHORT).show()
        }
    }
}
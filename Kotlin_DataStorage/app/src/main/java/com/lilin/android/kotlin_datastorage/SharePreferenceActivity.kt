package com.lilin.android.kotlin_datastorage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.lilin.android.kotlin_datastorage.databinding.ActivitySharePreferenceBinding
import java.lang.StringBuilder

/**
 * 创建日期：2021/9/18 15:44
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_datastorage
 * 类说明：
 */

/**
 * 高阶函数在SharedPreference上的应用
 */
class SharePreferenceActivity : AppCompatActivity() {
    companion object{
        const val TAG = "SharePreferenceActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivitySharePreferenceBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        inflate.button1.setOnClickListener {
//            saveJava()
//            saveKotlin()
            saveSystem()
        }

        inflate.button2.setOnClickListener {
            val sp = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = sp.getString("name", "")
            name?.let { Log.e(TAG, "name==$name") }
            val age = sp.getInt("age",0)
            Log.e(TAG,"age == $age")
            val married = sp.getBoolean("married",false)
            Log.e(TAG,"married == $married")
        }
    }

    private fun saveSystem() {
        getSharedPreferences("data",Context.MODE_PRIVATE).edit {
            putString("name","Tom")
            putInt("age",28)
            putBoolean("married",false)
        }
    }

    /**
     * Kotlin思维
     */
    private fun saveKotlin() {
        getSharedPreferences("data",Context.MODE_PRIVATE).open {
            putString("name","tom")
            putInt("age",28)
            putBoolean("married",false)
        }
    }

    /**
     * java思维
     */
    private fun saveJava() {
        val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
        editor.putString("name","tom")
        editor.putInt("age",28)
        editor.putBoolean("married",false)
        editor.apply()
    }
}
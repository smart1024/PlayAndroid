package com.lilin.android.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.lilin.android.retrofittest.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    private val objectMapper = ObjectMapper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.btn1.setOnClickListener {
            val url = "https://api.muxiaoguo.cn/api/lishijr"
            HttpUtil.request(HttpUtil.GET,url,object: HttpCallback {
                override fun onSuccess(response: String) {
                    Log.e(TAG,response)
                    val readValue = objectMapper.readValue(response,
                        object : TypeReference<CommonResponse<List<History>>>() {})
                    println(readValue.data)
                }

                override fun onFailure(e: Exception) {
                    Log.e(TAG,"请求失败")
                }

            })
        }
        inflate.btn2.setOnClickListener {

        }
        inflate.btn3.setOnClickListener {

        }
    }

}
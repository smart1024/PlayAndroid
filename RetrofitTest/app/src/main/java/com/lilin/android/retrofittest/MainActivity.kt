package com.lilin.android.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.lilin.android.retrofittest.databinding.ActivityMainBinding
import okhttp3.Call
import okhttp3.Response
import retrofit2.http.POST
import java.io.IOException
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
            val url = "https://api.muxiaoguo.cn/api/lishijr"
            HttpUtil.request(HttpUtil.POST,url,object: okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG,"请求失败")
                }

                override fun onResponse(call: Call, response: Response) {
                    val string = response.body()?.string();
                    Log.e(TAG,"$string")
                    if (TextUtils.isEmpty(string)){
                        Log.e(TAG,"返回数据为空")
                        return
                    }
                    val readValue = objectMapper.readValue(string, object : TypeReference<CommonResponse<List<History>>>() {})
                    println(readValue.data)
                }

            })
        }
        inflate.btn3.setOnClickListener {

        }
    }

}
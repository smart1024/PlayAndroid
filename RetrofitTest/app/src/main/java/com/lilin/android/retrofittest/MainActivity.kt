package com.lilin.android.retrofittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.lilin.android.retrofittest.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Call
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }
    private val objectMapper = ObjectMapper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val baseUrl = "https://api.muxiaoguo.cn/"
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

            val appService = retrofit.create(AppService::class.java)

            appService.getHistoryData().enqueue(object: Callback<CommonResponse<List<History>>> {
                override fun onResponse(
                    call: retrofit2.Call<CommonResponse<List<History>>>,
                    response: retrofit2.Response<CommonResponse<List<History>>>
                ) {
                    Log.e(TAG,response.body().toString())
                    Log.e(TAG,"${response.body()?.data?.size}")
                }

                override fun onFailure(call: retrofit2.Call<CommonResponse<List<History>>>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

        inflate.btn4.setOnClickListener {
            val appService = ServiceCreator.create(AppService::class.java)
            appService.getHistoryData().enqueue(object :Callback<CommonResponse<List<History>>>{
                override fun onResponse(
                    call: retrofit2.Call<CommonResponse<List<History>>>,
                    response: retrofit2.Response<CommonResponse<List<History>>>
                ) {
                    Log.e(TAG,response.body().toString())
                    Log.e(TAG,"${response.body()?.data?.size}")
                }

                override fun onFailure(
                    call: retrofit2.Call<CommonResponse<List<History>>>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }

            })
        }

        inflate.btn5.setOnClickListener { //使用参数实化来优化实例创建
            val appService = ServiceCreator.create<AppService>()
            appService.getHistoryData().enqueue(object :Callback<CommonResponse<List<History>>>{
                override fun onResponse(
                    call: retrofit2.Call<CommonResponse<List<History>>>,
                    response: retrofit2.Response<CommonResponse<List<History>>>
                ) {
                    Log.e(TAG,response.body().toString())
                    Log.e(TAG,"${response.body()?.data?.size}")
                }

                override fun onFailure(
                    call: retrofit2.Call<CommonResponse<List<History>>>,
                    t: Throwable
                ) {
                    t.printStackTrace()
                }

            })
        }

        inflate.btn6.setOnClickListener {
            runBlocking {
                getHistoryData()
            }
        }

        inflate.btn7.setOnClickListener {
            GlobalScope.launch {
                try {
                    val appData = ServiceCreator.create<AppService>().getHistoryData().await()
                    Log.e(TAG,"$appData")
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 定义Call的扩展函数await()
     */
    suspend fun <T> retrofit2.Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: retrofit2.Call<T>, response: retrofit2.Response<T>) {
                    val body = response.body()
                    if (body == null) continuation.resumeWithException(RuntimeException("body == null"))
                    continuation.resume(body!!)
                }

                override fun onFailure(call: retrofit2.Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
    /**
     * suspendCoroutine函数的使用可实现匿名回调的复用
     */
    suspend fun getHistoryData(){
        try {
            val url = "https://api.muxiaoguo.cn/api/lishijr"
            val response = HttpUtil.request(HttpUtil.GET,url)
            println(response)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}
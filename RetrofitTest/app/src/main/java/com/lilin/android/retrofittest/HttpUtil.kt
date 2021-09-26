package com.lilin.android.retrofittest

import android.text.TextUtils
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 创建日期：2021/9/24 10:13
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

object HttpUtil {
    const val GET = "GET"
    const val POST = "POST"

    fun request(method:String,url:String,callback: HttpCallback){
        thread {
            var connection:HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(url)
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = method
                connection.connectTimeout = 30000
                connection.readTimeout = 30000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                callback.onSuccess(response.toString())
            }catch (e:Exception){
                e.printStackTrace()
                callback.onFailure(e)
            }finally {
                connection?.disconnect()
            }
        }
    }

    /**
     * Okhttp不指定默认get请求
     */
    fun request(method: String,url:String,callback: Callback){
        val client = OkHttpClient.Builder().addNetworkInterceptor(LoggingInterceptor()).build()
        val requestBuilder:Request.Builder
        if (TextUtils.equals(method, GET)){
            requestBuilder = Request.Builder().get()
        }else if(TextUtils.equals(method, POST)){
            val requestBody = FormBody.Builder().add("name","ZhangSan").build()
            requestBuilder = Request.Builder().post(requestBody)
        }else{
            requestBuilder = Request.Builder().get()
        }
        val build = requestBuilder.url(url).build()

        //call入列
        client.newCall(build).enqueue(callback)
    }

    /**
     * 使用suspendCoroutine函数实现匿名回调的复用
     * request执行return后协程会挂起，网络请求线程会继续执行，请求返回后，协程被唤醒返回给调用处
     */
    suspend fun request(method: String,url: String):String{
        return suspendCoroutine { continuation ->
            request(method,url,object :Callback{
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.body() == null) continuation.resumeWithException(RuntimeException("response.body() == null"))
                    continuation.resume(response.body()!!.string())
                }

            })
        }
    }
}

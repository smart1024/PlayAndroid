package com.lilin.android.retrofittest

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

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
}

package com.lilin.android.retrofittest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * 创建日期：2021/9/24 17:22
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

class LoggingInterceptor : Interceptor {
    companion object{
        const val TAG = "LoggingInterceptor"
    }
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        Log.e(
            TAG,
            String.format(
                "Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()
            )
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        Log.e(
            TAG,
            String.format(
                "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()
            )
        )
        return response
    }
}
package com.lilin.android.retrofittest

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

/**
 * 创建日期：2021/9/26 10:27
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 * 将retrofit请求进行封装为单例类
 */

object ServiceCreator {
    private const val BASE_URL = "https://api.muxiaoguo.cn/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun <T> create(serviceClazz:Class<T>):T = retrofit.create(serviceClazz)

    /**
     * 运用参数实化优化一下create方法调用
     */
    inline fun <reified T> create():T = create(T::class.java)
}
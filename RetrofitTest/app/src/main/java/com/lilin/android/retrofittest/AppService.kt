package com.lilin.android.retrofittest

import retrofit2.Call
import retrofit2.http.GET

/**
 * 创建日期：2021/9/24 17:48
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

interface AppService {
    @GET("api/lishijr")
    fun getHistoryData(): Call<CommonResponse<List<History>>>
}
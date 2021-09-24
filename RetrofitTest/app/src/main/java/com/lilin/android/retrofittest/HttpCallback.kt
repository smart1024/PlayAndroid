package com.lilin.android.retrofittest

import java.lang.Exception

/**
 * 创建日期：2021/9/24 10:15
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

interface HttpCallback {
    fun onSuccess(response: String)
    fun onFailure(e: Exception)
}
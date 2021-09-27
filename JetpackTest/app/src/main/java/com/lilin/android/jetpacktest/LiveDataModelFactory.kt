package com.lilin.android.jetpacktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 创建日期：2021/9/27 16:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

class LiveDataModelFactory(private val counterReserved:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LiveDataModel(counterReserved) as T
    }
}
package com.lilin.android.jetpacktest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 16:10
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

class LiveDataModel(countReserved:Int):ViewModel() {
    val counter = MutableLiveData<Int>()
    init {
        counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value ?: 0
        counter.value = count+1
    }

    fun clear(){
        counter.value = 0
    }
}
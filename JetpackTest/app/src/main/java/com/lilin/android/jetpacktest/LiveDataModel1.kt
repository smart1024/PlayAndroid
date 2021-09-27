package com.lilin.android.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 16:10
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * 让ViewModel外部只能读LiveData
 */
class LiveDataModel1(countReserved:Int):ViewModel() {

    val counter:LiveData<Int> get() = _counter

    private val _counter = MutableLiveData<Int>()
    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value ?: 0
        _counter.value = count+1
    }

    fun clear(){
        _counter.value = 0
    }
}
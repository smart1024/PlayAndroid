package com.lilin.android.jetpacktest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 17:53
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * ViewModel中某个获取数据的方法有可能是没有参数的，
 * 这个时候代码应该怎么写呢？
 *
 * 在refresh()方法中，我们只是将refreshLiveData原有的数据取出来（默认是空）
 * ，再重新设置到refreshLiveData当中，这样就能触发一次数据变化,观察者就能得到最新的数据
 */
class SwitchMapViewModel1:ViewModel() {
    private val refreshLiveData = MutableLiveData<Any?>()
    val refreshResult = Transformations.switchMap(refreshLiveData){
        Repository.fresh()
    }

    fun refresh(){
        refreshLiveData.value = refreshLiveData.value
    }
}
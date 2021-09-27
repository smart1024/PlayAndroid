package com.lilin.android.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * 创建日期：2021/9/27 17:14
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

object Repository {
    fun getUser(userId:String):LiveData<User>{
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId,userId,0)
        return liveData
    }

    fun fresh():LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User("","",0)
        return liveData
    }
}
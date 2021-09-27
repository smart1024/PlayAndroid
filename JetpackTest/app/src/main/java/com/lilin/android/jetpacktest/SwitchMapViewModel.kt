package com.lilin.android.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 17:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * viewModel.getUser(userId).observe(this){user->}
 * 这个用法是错误的
 *
 * 因为每次调用getUser()方法返回的都是一个新的LiveData实例，
 * 而上述写法会一直观察老的LiveData实例，
 * 从而根本无法观察到数据的变化。
 * 你会发现，这种情况下的LiveData是不可观察的
 *
 * 这时候switchMap()可以发挥作用了
 */
class SwitchMapViewModel():ViewModel() {
    private val userLiveData = MutableLiveData<String>()

    var user = Transformations.switchMap(userLiveData){
        userId-> Repository.getUser(userId)
    }
//    fun getUser(userId:String):LiveData<User>{
//        return Repository.getUser(userId)
//    }

    fun getUser(userId:String){
        userLiveData.value = userId
    }
}
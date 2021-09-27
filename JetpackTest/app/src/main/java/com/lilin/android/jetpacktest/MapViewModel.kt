package com.lilin.android.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 17:06
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

class MapViewModel: ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    //map方法对LiveData进行转换
    val userName:LiveData<String> = Transformations.map(userLiveData){ user-> "${user.firstName} ${user.lastName}"
    }
}
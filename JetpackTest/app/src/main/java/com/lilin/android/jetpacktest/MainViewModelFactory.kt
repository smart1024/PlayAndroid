package com.lilin.android.jetpacktest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * 创建日期：2021/9/27 14:38
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * 如何向MainViewModel的构造函数传递数据了，
 * 需要借助ViewModelProvider.Factory
 *
 * 创建ViewMode的工厂类
 */
class MainViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}
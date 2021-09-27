package com.lilin.android.jetpacktest

import androidx.lifecycle.ViewModel

/**
 * 创建日期：2021/9/27 14:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * 比较好的编程规范是给每一个Activity和Fragment都创建一个对应的ViewModel
 */
class MainViewModel(countReserved:Int): ViewModel() {
    var counter = countReserved
}
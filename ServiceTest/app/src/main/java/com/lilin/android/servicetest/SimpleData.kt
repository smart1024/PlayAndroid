package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 16:07
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

class SimpleData<T> {
    private var data: T? = null

    /**
     * 泛型在in写入位置
     * 设置值
     */
    fun setValue(t: T?){
        data = t
    }

    /**
     * T在返回值，泛型在out输出位置
     */
    fun get():T?{
        return data
    }
}
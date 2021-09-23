package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 18:19
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 */

interface Transformer<in T> {
    fun transform(t:T):String
}
package com.lilin.android.retrofittest

/**
 * 创建日期：2021/9/24 10:06
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * jackson解析json是bean必须要默认构造函数，
 *  主构造函数每个参数有默认值时，才有默认构造函数
 */
data class CommonResponse<T>(val code: String = "", val msg:String="", val data: T? =null)
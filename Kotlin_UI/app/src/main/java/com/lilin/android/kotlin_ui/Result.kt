package com.lilin.android.kotlin_ui

/**
 * 创建日期：2021/9/17 15:53
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

/**
 * 定义了Result接口
 * 并定义两个类实现Result接口
 */
interface Result

class Success(val msg:String):Result
class Failure(val err:Exception):Result

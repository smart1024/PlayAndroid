package com.lilin.android.retrofittest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * 创建日期：2021/9/26 15:35
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * 调用withContext()函数之后，会立即执行代码块中的代码，同时将外部协程挂起。当代码块中的代码全部执行完之后，
 * 会将最后一行的执行结果作为withContext()函数的返回值返回，
 * 因此基本上相当于val result =async{ 5 + 5 }.await()的写法。
 * 唯一不同的是，withContext()函数强制要求我们指定一个线程参数
 */

/**
 * 线程参数
 * Dispatchers.Default：
 * 默认低并发的线程策略，计算密集型任务，过多的协程反而影响协程性能
 *
 * Dispatchers.IO：
 * 表示会使用一种较高并发的线程策略，当你要执行的代码大多数时间是在阻塞和等待中，为了提高执行效率
 *
 * Dispatchers.Main：
 * 则表示不会开启子线程，而是在Android主线程中执行代码，kotlin代码中使用这个线程参数会出错
 *
 *  除coroutineScope函数不需要指定的线程参数外，其他都有线程参数选项，withContext是必须制定线程参数
 */
fun main(){
    runBlocking {
        val result = withContext(Dispatchers.Default){
            5+5
        }
        println(result)
    }
}
package com.lilin.android.retrofittest

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 创建日期：2021/9/26 14:38
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.retrofittest
 * 类说明：
 */

/**
 * 实际项目中协程是这么用的
 * val job = Job()
 * val scope = CoroutineScope(job)
 * scope.launch{
 *  ...
 * }
 * job.cancel()
 *
 * runBlocking适合做测试时使用
 *
 * 你已经知道了调用launch函数可以创建一个新的协程，
 * 但是launch函数只能用于执行一段逻辑，却不能获取执行的结果，
 * 因为它的返回值永远是一个Job对象。那么有没有什么办法能够创建一个协程并获取它的执行结果呢？
 * 当然有，使用async函数就可以实现
 *
 * await()函数会立即获取值，如果协程没执行完，会阻塞协程
 * await在计算时候才获取值，比较高效，见testAsync2()
 *
 */
fun main(){
    testAsync()
    testAsync1()
    testAsync2()
}


/**
 *
 testAsync2 result is 21
testAsync2 cost 1005
 可见async实现了并行运行，await函数在
 */
fun testAsync2() {
    val start = System.currentTimeMillis()
    runBlocking {
        val result1 = async {
            delay(1000)
            5+5
        }
        val result2 = async {
            delay(1000)
            6+4
        }
        println("testAsync2 result is ${result1.await()+result2.await()}")
        val end = System.currentTimeMillis()
        println("testAsync2 cost ${end-start}")
    }
}

/**
 * 耗时2011ms可见 两段async函数确实是串行关系,运行效率低，完全可以并行执行
 */
fun testAsync1() {
    val start = System.currentTimeMillis()
    runBlocking {
        val result1 = async {
            delay(1000)
            5+5
        }.await()

        val result2 = async {
            delay(1000)
            4+6
        }.await()

        println("testAsync1 result is ${result1+result2}")
        val end = System.currentTimeMillis()
        println("testAsync1 cost ${end-start}")
    }
}

/**
 * 在调用了async函数之后，代码块中的代码就会立刻开始执行。
 * 当调用await()方法时，如果代码块中的代码还没执行完，
 * 那么await()方法会将当前协程阻塞住，直到可以获得async函数的执行结果
 */
fun testAsync() {
    runBlocking {
        val result = async {
            5+5
        }.await()
        println(result)
    }
}

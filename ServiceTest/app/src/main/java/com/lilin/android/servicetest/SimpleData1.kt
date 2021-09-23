package com.lilin.android.servicetest

/**
 * 创建日期：2021/9/23 16:17
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.servicetest
 * 类说明：
 * 一个泛型类在其泛型类型的数据上是只读的话，
 * 那么它是没有类型转换安全隐患的。而要实现这一点，
 * 则需要让MyClass<T>类中的所有方法都不能接收T类型的参数。换句话说，
 * T只能出现在out位置上，而不能出现在in位置上，对外部保持只读
 *
 * 你可能会说，构造函数中的泛型T不也是在in位置上的吗？
 * 没错，但是由于这里我们使用了val关键字，所以构造函数中的泛型T仍然是只读的，
 * 因此这样写是合法且安全的，没有改变T泛型对外部只读特点
 * 只要保证包保证泛型为只读
 */
/**
 * 进行了泛型协变声明
 *  SimpleData1在泛型T上是协变的
 */
class SimpleData1<out T>(private val data:T?) {
    fun get():T?{
        return data
    }
}
package com.lilin.android.kotlin_ui

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

/**
 * 创建日期：2021/9/17 15:53
 * @author lilin
 * @version 1.0
 * 包名：
 * 类说明：
 * fun getRandomLengthString(str:String):String{
 *      val n = (1..20).random()
 *      val builder = StringBuilder()
 *      repeat(n){
 *          builder.append(str)
 *      }
 *      return builder.toString()
 * }
 */

fun main(){
    getLetterCount()
    getLetterCount1()
    operaterOverride()
    operaterOverride1()
    operaterOverride2()
    println(getRandomLengthString("hello"))

    highOrderFunc()
    highOrderFunc1()
    highOrderFunc2()
}

fun highOrderFunc2() {
    val list = listOf("Apple","Orange","Banana","Pear","Grape")
    val result = StringBuilder().build { //lambda表达式最后一行最为函数返回值
        append("start eating fruits.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("eating all fruits.\n")
    }

}

/**
 * 使用Lambda表达式作为高阶函数的参数
 */
fun highOrderFunc1() {
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1,num2){ num1,num2 ->
        num1+num2
    }
    val result2 = num1AndNum2(num1,num2){ num1,num2 ->
        num1-num2
    }
    println("highOrderFunc1 result1===$result1")
    println("highOrderFunc1 result2===$result2")
}

/**
 * ::表示函数引入,表示将plus函数和minus函数作为参数传递
 */
fun highOrderFunc() {
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1,num2, ::plus)
    val result2 = num1AndNum2(num1,num2, ::minus)
    println("highOrderFunc result1===$result1")
    println("highOrderFunc result2===$result2")
}

fun getRandomLengthString(str:String) = str * (1..20).random()

/**
 * 扩展函数中重载*运算符
 */
fun operaterOverride2() {
    val str = "abc" * 3
    println("abc*3 ==== $str")
}

/**
 * in 实际调用的 contains
 */
fun operaterOverride1() {
    if ("hello".contains("he")){
        println("hello 包含 he")
    }

    if ("he" in "hello"){
        println("he in hello")
    }
}

/**
 * 运算符重载
 */
fun operaterOverride(){
    //加Money
    val m1 = Money(5)
    val m2 = Money(10);
    val m3 = m1+m2
    println("m1+m2===${m3.value}")

    //加数字
    val m4 = Money(20)
    val m5 = m4 + 10
    println("m4+m5===${m5.value}")
}

fun getLetterCount1() {
    val count = "ABC123xyz%@".lettersCount()
    println("字母的个数为 $count")
}

fun getLetterCount() {
    val str = "ABC123xyz%@"
    val count = StringUtil.lettersCount(str)
    println("字母的个数为 $count")
}

/**
 * 1、密封函数的引出
 * Result的执行结果只可能是Success或者Failure,else分支不可能走到，但必须满足语法检查而已
 * 这种为了满足编译器的要求而编写无用条件分支的情况不仅在Kotlin当中存在，
 *
 * 编写else条件还有一个潜在的风险。如果我们现在新增了一个Unknown类并实现Result接口，用于表示未知的执行结果，
 * 但是忘记在getResultMsg()方法中添加相应的条件分支，编译器在这种情况下是不会提醒我们的，
 * 而是会在运行的时候进入else条件里面，从而抛出异常并导致程序崩溃
 *
 * 在Java或者是其他编程语言当中也普遍存在
 * 为了解决这个问题 Kotlin设计了sealed class 密封类
 */
fun getResultMsg(result: Result) = when(result){
    is Success -> result.msg
    is Failure -> result.err
    else -> throw IllegalArgumentException()
}

/**
 * 去掉else条件仍然能编译通过呢？
 * 这是因为当在when语句中传入一个密封类变量作为条件时，
 * Kotlin编译器会自动检查该密封类有哪些子类，
 * 并强制要求你将每一个子类所对应的条件全部处理
 */
fun getResultMsg1(result: Result1) = when(result){
    is Success1 -> result.msg
    is Failure1 -> result.err
}

/**
 * 2、扩展函数：在不修改某个类的源码的情况下，向该类添加新的函数
 * 见StringUtil和String类
 */

/**
 * 3、运算符重载
 * 具体参见Money类
 */

/**
 * 4、高阶函数：能接收函数类型参数或者返回值为函数类型
 *
 * 函数类型的定义：
 * (String,Int) -> Unit
 *
 * ->左边是参数列表，多个参数逗号隔开，无参数用()
 * ->右边是函数返回值，无返回值使用Unit，相当于java的void
 *  高阶函数举例
 *
 *  fun example(fuc:(String,Int)->Unit){
 *      fuc("hello",123)
 *  }
 *  高阶函数允许让函数类型的参数来决定函数的执行逻辑
 *
 *  使用参见HighOrderFunction.kt
 *
 *  使用这种函数引用的写法虽然能够正常工作，
 *  但是如果每次调用任何高阶函数的时候都还得先定义一个与其函数类型参数相匹配的函数，
 *  这是不是有些太复杂了？
 *
 *  因此Kotlin引入了多种方式调用高阶函数
 *  1》Lambda表达式：Lambda表达式作为最后一个参数，可以移到()外
 *  2》匿名函数
 *  3》成员引用
 *
 *  最后高阶函数接收函数参数是，实际上每次都是创建1个函数类型的对象，会造成内存和性能开销
 *  因此引入高阶函数基本都会声明为inline(内联函数)，内联函数在Kotlin编译器的作用下，最终
 *  被替换最终的操作
 *
 val result1 = num1AndNum2(num1,num2){ num1,num2 ->
        num1+num2
}
 被优化为 val result1 = num1+num2

可以认为内联函数本质是代码的替换，不是真正意义上的函数
见LearnKotlin1 printString()函数

 因此内联函数才能消除高阶函数带来的性能开销

一个高阶函数中如果接收了两个或者更多函数类型的参数，
这时我们给函数加上了inline关键字，
那么Kotlin编译器会自动将所有引用的Lambda表达式全部进行内联
 怎么解决这个问题？使用noinline 声明该函数参数

 inline fun funTest(block1:()->Unit,noinline block2:()->Unit){}
现在Kotlin编译器只会对block1进行内联
 */


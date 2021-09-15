package com.lilin.android.kotlin_helloworld

import kotlin.math.max

/**
 * 创建日期：2021/9/14 16:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：在Android studio中独立运行kotlin代码
 * new 选择File通常是用于编写Kotlin顶层函数和扩展函数的
 */

/**
 * 1、Kotlin变量的定义
 */
fun main(){
    println("Hello Kotlin")

    /**
     * 定义final变量
     * kotlin类型推导机制
     */
    val a = 10
    println("a= "+a)

    val b = 37
    val c = 40
    val value = largeNumber(b,c)

    //表示一个区间[0,10]代表双端闭区间
    val range = 0..10

    //for循环
    for (i in range){
        println(i)
    }

    //表示一个[0,10)
    val range1 = 0 until 10
    //forin默认递增1，step可指定递增大小
    for (i in range1 step 2){
        println(i)
    }

    //downTo创建一个降序区间
    for (i in 10 downTo 1){
        println(i)
    }

    //字符串模板
    println("large Number is $value")

    checkNumber(10)


    /**
     * 4、对象的定义
     */
    val p = Person("Jack",19)
//    p.name = "Jack"
//    p.age = 19;
    p.eat()
}

/**
 * 2、Kotlin函数的定义
 */
/**当函数中只有一行代码，可以直接把这行代码用等号赋值给函数定义*/
//fun largeNumber(num1:Int,num2: Int):Int{
//    return max(num1,num2)
//}

//fun largeNumber(num1: Int,num2: Int):Int = max(num1,num2)

/**kotlin的类型推导：max函数返回Int型，因此可省略largeNumber的返回值类型*/
fun largeNumber(num1:Int,num2:Int) = max(num1,num2)

/**
 * 2、Kotlin的if语句
 */
/**Kotlin的if语句会有返回值，返回值为if语句每个条件的最后一行代码的返回值*/
//fun myLargeNumber(num1: Int,num2: Int):Int{
//    var value = 0;
//    if (num1 > num2){
//        value = num1
//    }else{
//        value = num2
//    }
//    return value
//}

//fun myLargeNumber(num1: Int,num2: Int):Int{
//    var value = if (num1 > num2){
//        num1
//    }else{
//        num2
//    }
//    return value
//}

/**省略return语句*/
//fun myLargeNumber(num1: Int,num2: Int):Int{
//    return if (num1>num2){
//        num1
//    }else{
//        num2
//    }
//}

/**省略{}*/
//fun myLargeNumber(num1: Int,num2: Int) = if (num1 > num2){
//    num1
//}else{
//    num2
//}

fun myLargeNumber(num1: Int,num2: Int)= if (num1>num2) num1 else num2

/**
 *  3、Kotlin的when语句
 */

//使用if实现，判断条件非常多
//fun getScore(name:String) = if (name == "Tom"){
//    80
//}else if (name == "Jim"){
//    77
//}else if (name == "Jack"){
//    95
//} else if (name == "Lily"){
//    100
//}else{
//    0
//}

//使用when精准匹配 类似java中的switch
//fun getScore(name:String) = when(name){
//    "Tom" -> 80
//    "Jim" -> 77
//    "jack"-> 95
//    "Lily"-> 100
//    else -> 0
//}

//when语句中不传入参数name
//fun getScore(name:String) = when{
//    name == "Tom" -> 80
//    name == "Jim" -> 77
//    name == "jack"-> 95
//    name == "Lily"->100
//    else -> 0
//}

//when实现以Tom开头的都是86分
fun getScore(name:String) = when{
    name.startsWith("Tome") -> 86
    name == "Jim" -> 77
    name == "jack"-> 95
    name == "Lily"->100
    else -> 0
}

//使用when和is关键字实现类型匹配
fun checkNumber(num:Number){
    when(num){
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

/**
 * 4、Kotlin的继承和构造函数
 *  1、Kotlin中的非抽象类默认不能被继承，相当于final类,要想被继承open关键字
 *  2、java中的继承使用extends关键字，Kotlin用:类型()
 *  3、class Student : Person()需要带上括号原因
 *  主构造函数和次构造函数
 *  主构造函数：每个类都带有默认的无参的主构造函数，当然你也可以指定参数，主构造函数特点是没有函数体
 *  class Student(val sno:String,val grade:Int) : Person(){}
 *  将学号和年级这两个字段都放到了主构造函数当中，这就表明在对Student类进行实例化的时候，必须传入构造函数中要求的所有参数
 *  val student = Student("a123",5)
 *  构造函数中的参数是在创建实例的时候传入的，不像之前的写法那样还得重新赋值，因此我们可以将参数全部声明成val
 *
 *  我们知道主构造函数没有函数体，如果想在主构造函数里添加逻辑怎么办？init代码块
 *
 *  子类必须调用父类的构造函数，具体调哪个构造函数通过Person()来指定
 *  val student = Student("a123",5,"Jack",19)
 *
 *  4、次构造函数：
 *  1）任何类只有一个主构造函数(也可以没有主构造函数)，次构造函数可以有多个
 *  2）次构造函数必须调用主构造函数
 *  5、没有主构造函数，只有次构造函数的情形
 *  请看Student1例子
 */


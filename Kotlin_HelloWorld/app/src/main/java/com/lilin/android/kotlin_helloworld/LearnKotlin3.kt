package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/16 15:45
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

/**
 * 10、空指针检查
 * 1）Kotlin将空指针异常的检查提前到了编译时期
 * 2）判空辅助工具
 * ?.操作符 如果是空啥都不做
 *  ?:操作符 取代if判断
 *  val c = if(a != null){
 *      a
 *  }else{
 *      b
 *  }
 *  简化为
 *  //如果a!=null 返回a，否则返回b
 *  val c = a ?: b
 *  3）!!非空断言工具
 *
 *  4）let函数，let不是操作符也不是关键字而是函数
 *  这个函数提供了函数式API的编程接口，并将原始调用对象作为参数传递到Lambda表达式中
 *  见doStudy3，doStudy4使用 study为空时let函数不会执行
 *  5）字符串内嵌 "my name is $name"
 */
var content:String?  = "hello"

fun main(){
    doStudy1(null)
    doStudy2(null)

    if (content!=null){
        printUppercase()
    }
}

//原始调用对象作为参数传递到Lambda表达式中
fun doStudy3(study: Study?){
    study?.let { stu ->
        stu.readBooks()
        stu.doHomeWork()
    }
}

//lambda表达式只有一个参数，那么参数可用it代替
fun doStudy4(study: Study?){
    study?.let {
        it.readBooks()
        it.doHomeWork()
    }
}

//!!非空断言工具
fun printUppercase() {
    val str = content!!.uppercase()
}


fun getTextLength(text:String?):Int{
    if (text != null){
        return text.length
    }
    return 0
}

//将?.和?:操作符结合到了一起使用
fun getTextLength1(text:String?):Int{
    return text?.length ?: 0
}

/**
 * 类型后面加？号表示参数可空
 */
fun doStudy1(study: Study?){
    if (study != null){
        study.doHomeWork()
        study.readBooks()
    }
}

fun doStudy2(study: Study?){
    study?.doHomeWork()
    study?.readBooks()
}
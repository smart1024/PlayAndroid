package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/15 15:11
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

/**
 * 我们在Student类的主构造函数中增加name和age这两个字段时，
 * 不能再将它们声明成val，因为在主构造函数中声明成val或者var的参数将自动成为该类的字段，
 * 这就会导致和父类中同名的name和age字段造成冲突。因此，这里的name和age参数前面我们不用加任何关键字
 */
class Student(val sno:String,val grade:Int,name:String,age:Int) : Person(name,age) {
  init {
      println("sno is $sno")
      println("grade is $grade")
  }
    //次构造函数直接调用主构造函数
    constructor(name:String,age: Int) : this("",0,name,age)

    //次构造函数间接调用主构造函数
    constructor():this("",0)
}
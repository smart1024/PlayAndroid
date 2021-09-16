package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/16 15:09
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */

/**
 * Java函数式API的使用都限定于从Kotlin中调用Java方法，
 * 并且单抽象方法接口也必须是用Java语言定义的
 *  Kotlin的SAM（Single Abstract Method)转换
 * 9、Kotlin调用java函数式API
 *  Kotlin代码中调用了一个Java方法，SAM转换
 *  java 版本
 *  new Thread(new Runnable(){
 *      @Override
 *      public void run(){
 *          System.out.print("Thread is running");
 *      }
 *  }).start();
 *
 *  再举个例子 java中
 *  button.setOnClickListener(new View.onClickListener(){
 *      @Override
 *      public void onClick(View v){
 *
 *      }
 *  })
 *
 *  kotlin实现  省略接口名 省略单一抽象方法 省略圆括号
 *  button.setOnClickListener{
 *
 *  }
 */

fun main(){
    //翻译成Kotlin

    //创建匿名类实例的时候就不能再使用new了，Kotlin改用了object关键字
    Thread(object :Runnable{
        override fun run() {
            println("Thread is running")
        }
    }).start()

//简化 Thread类的构造方法是符合Java函数式API的使用条件,单一抽象方法声明可以省略不写
    Thread(Runnable { println("Thread is running") }).start()
    //继续简化 如果一个Java方法的参数列表中有且仅有一个Java单抽象方法接口参数，可以省略接口名
    Thread({println("Thread is running")}).start()

    //继续简化 当Lambda表达式是方法的最后一个参数，将Lambda表达式移到方法括号的外面
    Thread(){println("Thread is running")}.start()

    //继续简化 同时lambda表达式还是唯一一个参数()可以省略
    Thread{println("Thread is running")}.start()

}
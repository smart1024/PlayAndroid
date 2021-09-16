package com.lilin.android.kotlin_helloworld

/**
 * 创建日期：2021/9/16 11:06
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_helloworld
 * 类说明：
 */
/** java 单例写法
 * public class Singleton{
 *      private static Singleton instance;
 *      private Singleton(){};
 *      public synchronized static Singleton getInstance(){
 *          if(instance == null){
 *              instance = new Singleton();
 *          }
 *          return instance;
 *      }
 *      public void singletonTest(){
 *          System.out.println("singletonTest is called");
 *      }
 * }
 */

/**
 * Kotlin单例写法
 * object 类名  声明单例
 */
object Singleton {
   fun singletonTest(){
       println("singletonTest is called")
   }
}
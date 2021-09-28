package com.lilin.android.jetpacktest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * 创建日期：2021/9/28 10:55
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 * let的解释
 * https://cloud.tencent.com/developer/article/1591238
 *
 * AppDatabase类必须继承自RoomDatabase类，并且一定要使用abstract关键字将它声明成抽象类，
 * 然后提供相应的抽象方法，用于获取之前编写的Dao的实例，
 * 比如这里提供的userDao()方法。不过我们只需要进行方法声明就可以了，
 * 具体的方法实现是由Room在底层自动完成的
 */

@Database(version = 1,entities = [User::class])
abstract class AppDataBase :RoomDatabase() {
    abstract fun userDao():UserDao
    companion object{ //单例
        private var instance:AppDataBase?=null

        @Synchronized
        fun getDataBase(context: Context):AppDataBase{
            instance?.let { //instance不为空才执行return it
                return it
            }
            //apply返回传入的对象本身
            //context.applicationContext 不会出现内测泄漏
            return Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"app_database").build().apply {
                instance = this
            }
        }
    }
}
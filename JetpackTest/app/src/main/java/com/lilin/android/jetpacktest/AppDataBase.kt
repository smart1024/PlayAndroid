package com.lilin.android.jetpacktest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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

/**
 * 升级数据库
 */
@Database(version = 3,entities = [User::class,Book::class])
abstract class AppDataBase :RoomDatabase() {
    abstract fun userDao():UserDao

    abstract fun bookDao():BookDao

    companion object{ //单例

        val MIGRATION_1_2 = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id Integer primary key autoincrement not null,name text not null,pages Integer not null)")
            }
        }

        val MIGRATION_2_3 = object : Migration(2,3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance:AppDataBase?=null

        @Synchronized
        fun getDataBase(context: Context):AppDataBase{
            instance?.let { //instance不为空才执行return it
                return it
            }
            //apply返回传入的对象本身
            //context.applicationContext 不会出现内测泄漏
            return Room.databaseBuilder(context.applicationContext,AppDataBase::class.java,"app_database").addMigrations(
                MIGRATION_1_2, MIGRATION_2_3)
//                .fallbackToDestructiveMigration() //会销毁数据库，重新创建
                .build().apply {
                instance = this
            }
        }
    }
}
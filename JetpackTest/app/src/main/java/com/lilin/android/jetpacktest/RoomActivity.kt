package com.lilin.android.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.lilin.android.jetpacktest.databinding.ActivityRoomBinding
import kotlin.concurrent.thread

/**
 * 数据库操作属于耗时操作，Room不允许在主线程中访问
 */
class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityRoomBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        val userDao = AppDataBase.getDataBase(this).userDao()
        val user1 = User("Tom","Brady",40)
        val user2 = User("Tom","Hanks",63)

        val bookDao = AppDataBase.getDataBase(this).bookDao()
        val book1 = Book("三国演义",800)
        val book2 = Book("红楼梦",630)

        inflate.addDataBtn.setOnClickListener {
            thread {
                //将insertUser()方法返回的主键id值赋值给原来的User对象。之所以要这么做，
                // 是因为使用@Update和@Delete注解去更新和删除数据时都是基于这个id值来操作的
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)

                book1.id = bookDao.insertBook(book1)
                book2.id = bookDao.insertBook(book2)
            }
        }

        inflate.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        inflate.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }

        inflate.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.e("RoomActivity",user.toString())
                }

                for (book in bookDao.loadAllBook()){
                    Log.e("RoomActivity",book.toString())
                }
            }
        }
    }
}
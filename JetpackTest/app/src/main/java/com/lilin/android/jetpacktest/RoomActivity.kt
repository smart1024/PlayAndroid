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
        inflate.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
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
            }
        }
    }
}
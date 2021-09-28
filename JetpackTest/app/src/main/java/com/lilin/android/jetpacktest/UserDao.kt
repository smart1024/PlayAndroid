package com.lilin.android.jetpacktest

import androidx.room.*

/**
 * 创建日期：2021/9/28 10:37
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * @Dao注解 让Room识别
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User):Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers():List<User>

    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age:Int):List<User>

    @Delete
    fun deleteUser(user:User)

    @Query("delete from user where lastName = :lastName")
    fun deleteUserByLastName(lastName:String):Int
}
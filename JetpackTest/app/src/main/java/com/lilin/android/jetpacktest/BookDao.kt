package com.lilin.android.jetpacktest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * 创建日期：2021/9/28 11:46
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book):Long

    @Query("select * from Book")
    fun loadAllBook():List<Book>
}
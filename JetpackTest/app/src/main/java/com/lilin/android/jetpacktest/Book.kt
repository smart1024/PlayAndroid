package com.lilin.android.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 创建日期：2021/9/28 11:44
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

@Entity
data class Book(var name:String,var pages:Int){
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}

package com.lilin.android.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 创建日期：2021/9/27 17:08
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.jetpacktest
 * 类说明：
 */

/**
 * @Entity注解声明为实体类
 */
@Entity
data class User(var firstName:String,var lastName:String,var age:Int){

    //每个实体都需要有id字段并声明为主键，并且自动生成
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}

package com.lilin.android.kotlin_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.lilin.android.kotlin_activity.databinding.ActivitySendBinding

class SendActivity : BaseActivity() {
    //类似java中的static方法
    companion object{
        fun actionStart(context: Context,data1:String,data2: String){
//            val intent = Intent(context,MainActivity::class.java)
//            intent.putExtra("param1",data1)
//            intent.putExtra("param2",data2)
//            context.startActivity(intent)
            //使用apply函数精简代码
            val intent = Intent(context,MainActivity::class.java).apply {
                putExtra("param1",data1)
                putExtra("param2",data2)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivitySendBinding.inflate(layoutInflater)
        setContentView(inflate.root)
//        调用的顶层方法
        doSomething()
    }
}
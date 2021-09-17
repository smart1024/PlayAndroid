package com.lilin.android.kotlin_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.lilin.android.kotlin_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object{
        //常量只能在伴生类、顶层函数、单例类中使用
        const val TAG:String = "MainActivity"
    }
    //1、这种方式所有用到adapter的地方都需要判空
//    private var adapter:FruitAdapter? = null
    //2、告知Kotlin编译器 adapter 晚些时候会被初始化
    private lateinit var adapter: FruitAdapter
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        initFruits()
        adapter = FruitAdapter(fruitList)
        inflate.recyclerView.layoutManager = LinearLayoutManager(this)

        if (::adapter.isInitialized){ //判断是否被初始化
            inflate.recyclerView.adapter = adapter
        }


    }

    private fun initFruits() {
        //让水果重复两次
        repeat(2){
            fruitList.add(Fruit("Apple",R.mipmap.apple_pic))
            fruitList.add(Fruit("Banana",R.mipmap.banana_pic))
            fruitList.add(Fruit("Orange",R.mipmap.orange_pic))
            fruitList.add(Fruit("Watermelon",R.mipmap.watermelon_pic))
            fruitList.add(Fruit("Pear",R.mipmap.pear_pic))
            fruitList.add(Fruit("Grape",R.mipmap.grape_pic))
            fruitList.add(Fruit("Pineapple",R.mipmap.pineapple_pic))
            fruitList.add(Fruit("Strawberry",R.mipmap.strawberry_pic))
            fruitList.add(Fruit("Cherry",R.mipmap.cherry_pic))
            fruitList.add(Fruit("Mango",R.mipmap.mango_pic))
        }
    }
}
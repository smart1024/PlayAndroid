package com.lilin.android.kotlin_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.lilin.android.kotlin_ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)
        initFruits()
        inflate.recyclerView.layoutManager = LinearLayoutManager(this)
        inflate.recyclerView.adapter = FruitAdapter(fruitList)
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
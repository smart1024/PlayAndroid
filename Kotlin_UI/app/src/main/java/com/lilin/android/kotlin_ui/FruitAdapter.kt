package com.lilin.android.kotlin_ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.lilin.android.kotlin_ui.databinding.FruitItemBinding

/**
 * 创建日期：2021/9/17 14:36
 * @author lilin
 * @version 1.0
 * 包名： com.lilin.android.kotlin_ui
 * 类说明：
 */

class FruitAdapter(val list: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    //定义内部类ViewHolder
    inner class ViewHolder(binding: FruitItemBinding):RecyclerView.ViewHolder(binding.root){
        val fruitImage:ImageView = binding.fruitImage
        val fruitName:TextView = binding.fruitName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = FruitItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = list[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount() = list.size

}
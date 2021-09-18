package com.lilin.android.kotlin_datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.lilin.android.kotlin_datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.button1.setOnClickListener {
            Toast.makeText(this,"button1 clicked",Toast.LENGTH_SHORT).show()
        }

        inflate.button2.setOnClickListener {
            Toast.makeText(this,"button3 clicked",Toast.LENGTH_SHORT).show()
        }
        inflate.button3.setOnClickListener {
            Toast.makeText(this,"button3 clicked",Toast.LENGTH_SHORT).show()
        }
        inflate.button4.setOnClickListener {
            Toast.makeText(this,"button4 clicked",Toast.LENGTH_SHORT).show()
        }
    }
}
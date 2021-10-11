package com.lilin.android.todayinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.lilin.android.todayinformation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.lav1.playAnimation()

        inflate.lav1.setOnClickListener {
            inflate.lav1.playAnimation()
            inflate.lav2.reverseAnimationSpeed()
        }

        inflate.lav2.setOnClickListener {
            inflate.lav2.playAnimation()
            inflate.lav1.reverseAnimationSpeed()
        }
    }
}
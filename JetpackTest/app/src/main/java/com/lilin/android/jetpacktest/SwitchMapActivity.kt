package com.lilin.android.jetpacktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.lilin.android.jetpacktest.databinding.ActivitySwitchMapBinding

class SwitchMapActivity : AppCompatActivity() {
    lateinit var inflate: ActivitySwitchMapBinding
    lateinit var viewModel:SwitchMapViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SwitchMapViewModel::class.java]
        inflate = ActivitySwitchMapBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.getUser.setOnClickListener {
            inflate.getUser.setOnClickListener {
                val userId = (0..10000).random().toString()
                viewModel.getUser(userId)
            }
        }

        viewModel.user.observe(this){ user->
            inflate.textInfo.text = user.firstName
        }
    }
}
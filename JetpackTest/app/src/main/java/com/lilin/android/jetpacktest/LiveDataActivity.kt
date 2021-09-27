package com.lilin.android.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lilin.android.jetpacktest.databinding.ActivityLiveDataBinding

class LiveDataActivity : AppCompatActivity() {
    lateinit var viewModel:LiveDataModel
    lateinit var inflate: ActivityLiveDataBinding
    lateinit var sp:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = getPreferences(Context.MODE_PRIVATE)
        val counterReserved = sp.getInt("counter_reserved", 0)
        viewModel = ViewModelProvider(this,LiveDataModelFactory(counterReserved))[LiveDataModel::class.java]
        inflate = ActivityLiveDataBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        inflate.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }

        inflate.clearBtn.setOnClickListener {
            viewModel.clear()
        }

        //LiveData 观察ViewMode的数据变化，更新UI
        viewModel.counter.observe(this, { count->
            inflate.infoText.text = "$count"
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("counter_reserved",viewModel.counter.value ?: 0)
        }
    }
}
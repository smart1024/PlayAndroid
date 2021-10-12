package com.lilin.android.todayinformation
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.lilin.android.todayinformation.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var timer: CustomCountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflate = ActivitySplashBinding.inflate(LayoutInflater.from(this))
        setContentView(inflate.root)

        val uri = Uri.parse("android.resource://com.lilin.android.todayinformation/"+R.raw.splash)
        inflate.videoView.setVideoURI(uri)
        inflate.videoView.setOnPreparedListener {
            it.start()
        }

        inflate.videoView.setOnCompletionListener {
            it.start()
        }

        inflate.tvCountDown.isEnabled = false
        timer = CustomCountDownTimer(3,1000,object :CustomCountDownTimer.ICountDownHandler{
            override fun onTick(time: Long) {
                inflate.tvCountDown.text = "$time S"
            }

            override fun onFinish() {
                inflate.tvCountDown.text = "跳转"
                inflate.tvCountDown.isEnabled = true
            }

        })
        timer.startCountDown()

        inflate.tvCountDown.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        timer.cancelCountDown()
        super.onDestroy()
    }
}
package com.lilin.android.todayinformation
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.lilin.android.todayinformation.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
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
    }
}
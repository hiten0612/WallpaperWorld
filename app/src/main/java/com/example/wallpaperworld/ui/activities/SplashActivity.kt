package com.example.wallpaperworld.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.wallpaperworld.R
import com.example.wallpaperworld.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val content = findViewById<View>(android.R.id.content)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            content.viewTreeObserver.addOnDrawListener {
//                false
//            }
//        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Animatoo.animateSlideLeft(this);
            finish()
        }, 3000)    }
}
package com.example.wallpaperworld.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.example.wallpaperworld.R
import com.example.wallpaperworld.databinding.ActivityFullScreenBinding
import com.example.wallpaperworld.databinding.ActivityWallPaperBinding

class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.extras?.getString("wallImage")

        Glide.with(this)
            .load(imageUrl)

            .into(binding.fullImage)

        onClick()

    }

    override fun onBackPressed() {
        finish()
        Animatoo.animateSlideRight(this)
    }

    private fun onClick() {

        binding.cardShare.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_STREAM,
                    "https://cdn.pixabay.com/photo/2020/11/22/17/28/cat-5767334_960_720.jpg"
                )
                type = "image/jpeg"
            }
            startActivity(Intent.createChooser(shareIntent, null))
        }
        binding.imgBack.setOnClickListener {
            finish()
            Animatoo.animateSlideRight(this);

        }

        binding.cardPreview.setOnClickListener {
            toggle()

        }
        binding.root.setOnClickListener {

            toggle(true)
        }
    }

    private fun toggle(show: Boolean = false) {

        val transition: Transition = Fade()
        transition.duration = 500
        transition.addTarget(binding.llBottom)
        transition.addTarget(binding.imgBack)
        TransitionManager.beginDelayedTransition(binding.llBottom, transition)
        if (show) {
            binding.llBottom.visibility = View.VISIBLE
            binding.imgBack.visibility = View.VISIBLE
        } else {
            binding.llBottom.visibility = View.GONE
            binding.imgBack.visibility = View.GONE
        }
    }
}
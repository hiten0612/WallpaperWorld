package com.example.wallpaperworld.ui.activities

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.example.wallpaperworld.databinding.ActivityFullScreenBinding
import java.io.File


class FullScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFullScreenBinding
    private var imageUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUrl = intent.extras?.getString("wallImage")

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
        binding.cardDownload.setOnClickListener {
            downloadImageNew(imageUrl!!)
        }
    }


    private fun downloadImageNew(downloadUrlOfImage: String) {
        try {
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri: Uri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    File.separator + ".jpg"
                )
            dm.enqueue(request)
            Toast.makeText(this,     "Image download started.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("exce=======", e.message.toString())
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show()
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
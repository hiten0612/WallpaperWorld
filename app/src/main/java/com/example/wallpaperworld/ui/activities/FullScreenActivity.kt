package com.example.wallpaperworld.ui.activities

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment.DIRECTORY_PICTURES
import android.os.Handler
import android.os.Looper
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.example.wallpaperworld.databinding.ActivityFullScreenBinding
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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
            shareImageFromURI(imageUrl)
//            val sharingIntent = Intent(Intent.ACTION_SEND)
//            val imageUri = Uri.parse(imageUrl)
//            sharingIntent.type = "image/jpeg"
//            sharingIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
//            startActivity(sharingIntent)

        }
        binding.imgBack.setOnClickListener {
            finish()
            Animatoo.animateSlideRight(this);
        }

//        binding.cardPreview.setOnClickListener {
//            toggle()
//        }
//        binding.root.setOnClickListener {
//            toggle(true)
//        }
        binding.cardDownload.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.imgDownload.visibility = View.GONE
            downloadImageNew(imageUrl!!)
            // shareImageFromURI(imageUrl)
        }
    }


    private fun downloadImageNew(downloadUrlOfImage: String) {
        try {

            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val downloadUri: Uri = Uri.parse(downloadUrlOfImage)
            val request = DownloadManager.Request(downloadUri)
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle("Wallpaper World ")
                .setMimeType("image/jpeg") // Your file type. You can use this code to download other file types also.
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    DIRECTORY_PICTURES,
                    File.separator + "Wallpaper World" + File.separator + "Wallpaper_${System.currentTimeMillis()}.jpg"
                )

            dm.enqueue(request)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.visibility = View.GONE
                binding.imgDownload.visibility = View.VISIBLE
            }, 4000)


//            Toast.makeText(this, "Image downloaded.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("exce=======", e.message.toString())
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show()
        }
    }

    fun shareImageFromURI(url: String?) {
        Picasso.get().load(url).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, getBitmapFromView(bitmap))
                startActivity(Intent.createChooser(intent, "Share Image"))

            }

            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                TODO("Not yet implemented")
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

        })
    }

    fun getBitmapFromView(bmp: Bitmap?): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(this.externalCacheDir, System.currentTimeMillis().toString() + ".jpg")
            val out = FileOutputStream(file)
            bmp?.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.close()
//            bmpUri = Uri.fromFile(file)
            bmpUri = FileProvider.getUriForFile(
                this, this.applicationContext.packageName + ".provider",
                file
            );

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
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
package com.example.wallpaperworld.ui.activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.os.Build.VERSION.SDK_INT
import android.os.Environment.DIRECTORY_PICTURES
import android.provider.Settings
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    private val PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUrl = intent.extras?.getString("wallImage")

        Glide.with(this)
            .load(imageUrl)

            .into(binding.fullImage)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(binding.imgDevice, "translationY", 500.0f, 0.0f),
            ObjectAnimator.ofFloat(binding.imgDevice, "alpha", 0.0f, 1.0f),
            ObjectAnimator.ofFloat(binding.fullImage, "translationY", 500.0f, 0.0f),
            ObjectAnimator.ofFloat(binding.fullImage, "alpha", 0.0f, 1.0f),
        )
        animatorSet.duration = 2000
        animatorSet.start()

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


        binding.cardDownload.setOnClickListener {

            if (checkPermission()) {
                binding.imgDownload.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.view.visibility = View.VISIBLE
                binding.downloadProgress.visibility = View.VISIBLE
                downloadImageNew(imageUrl!!)
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        return if (SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            val result =
                ContextCompat.checkSelfPermission(this@FullScreenActivity, READ_EXTERNAL_STORAGE)
            val result1 =
                ContextCompat.checkSelfPermission(this@FullScreenActivity, WRITE_EXTERNAL_STORAGE)
            result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data =
                    Uri.parse(String.format("package:%s", applicationContext.packageName))
                startActivityForResult(intent, 2296)
            } catch (e: java.lang.Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, 2296)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                this@FullScreenActivity,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2296) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.size > 0) {
                //val READ_EXTERNAL_STORAGE = grantResults[0] === PackageManager.PERMISSION_GRANTED
                val WRITE_EXTERNAL_STORAGE = grantResults[0] == PackageManager.PERMISSION_GRANTED
                if (WRITE_EXTERNAL_STORAGE) {
                    // perform action when allow permission success

                    Toast.makeText(this, " permission granted", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "permission not granted yet", Toast.LENGTH_SHORT)
                        .show()
                }
            }
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
                binding.view.visibility = View.GONE
                binding.downloadProgress.visibility = View.GONE
                binding.imgDownload.visibility = View.VISIBLE
            }, 5000)


//            Toast.makeText(this, "Image downloaded.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("exce=======", e.message.toString())
            Toast.makeText(this, "Image download failed.", Toast.LENGTH_SHORT).show()
        }
    }
//
//    fun checkPermission(): Boolean {
//        val result = ContextCompat.checkSelfPermission(
//            this@FullScreenActivity,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//        )
//        if (result == PackageManager.PERMISSION_GRANTED) {
//            return true
//        } else {
//            return false
//        }
//    }
//
//    private fun requestPermission() {
//        if (ActivityCompat.shouldShowRequestPermissionRationale(
//                this@FullScreenActivity,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE
//            )
//        ) {
//            Toast.makeText(
//                this@FullScreenActivity,
//                "Write External Storage permission allows us to save files. Please allow this permission in App Settings.",
//                Toast.LENGTH_LONG
//            ).show()
//        } else {
//            ActivityCompat.requestPermissions(
//                this@FullScreenActivity,
//                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                PERMISSION_REQUEST_CODE
//            )
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                Log.e("value", "Permission Granted, Now you can use local drive .");
//            } else {
//                Log.e("value", "Permission Denied, You cannot use local drive .");
//            }
//        }
//    }


    private fun shareImageFromURI(url: String?) {
        Picasso.get().load(url).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, getBitmapFromView(bitmap))
                startActivity(Intent.createChooser(intent, "Share Image"))

            }

            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {


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
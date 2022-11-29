package com.example.wallpaperworld.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object CustomBindAdapter {

    @JvmStatic
    @BindingAdapter("app:imageSrc")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
}
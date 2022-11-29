package com.example.wallpaperworld.remote

import com.example.wallpaperworld.models.Photo
import com.example.wallpaperworld.models.WallPaperModel
import com.example.wallpaperworld.viewmodels.WallPaperViewModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {

    @GET("search")
    suspend fun getImages(
        @Header("Authorization") token: String,
        @Query("query") images: String,
        @Query("per_page") limit: Int = 80,
    ) : Response<WallPaperModel>
}
package com.example.wallpaperworld.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getImages(auth : String,imageUrl  : String) = apiInterface.getImages(auth,imageUrl)
}
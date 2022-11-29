package com.example.wallpaperworld.remote

import com.example.wallpaperworld.models.Photo
import com.example.wallpaperworld.models.WallPaperModel
import com.example.wallpaperworld.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiCall() {

    suspend fun getImages(auth : String, imageUrl: String): Flow<NetworkResult<WallPaperModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getImages(auth,imageUrl) })
        }.flowOn(Dispatchers.IO)
    }
    

}
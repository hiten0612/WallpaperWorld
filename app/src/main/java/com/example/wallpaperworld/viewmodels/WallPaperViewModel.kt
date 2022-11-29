package com.example.wallpaperworld.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wallpaperworld.models.Photo
import com.example.wallpaperworld.models.WallPaperModel
import com.example.wallpaperworld.remote.AppRepository
import com.example.wallpaperworld.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WallPaperViewModel @Inject constructor(
    private val repository: AppRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<WallPaperModel>> = MutableLiveData()
    val response: LiveData<NetworkResult<WallPaperModel>> = _response


     fun getImages(auth : String, imageUrl: String) = viewModelScope.launch {
        repository.getImages(auth,imageUrl).collect { value ->
            _response.value = value

        }
    }
}
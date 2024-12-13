package com.bangkit.agrotentionapp.view.result

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.agrotentionapp.data.remote.NewsRepository
import com.bangkit.agrotentionapp.data.remote.Result
import com.bangkit.agrotentionapp.data.remote.response.ScanResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ResultViewModel (private val newsRepository: NewsRepository) : ViewModel() {

    private val _scanResponse = MutableLiveData<Result<ScanResponse>>()
    val scanResponse: LiveData<Result<ScanResponse>> = _scanResponse

    private val _currentImageUri = MutableLiveData<Uri?>()
    val currentImageUri: LiveData<Uri?> = _currentImageUri

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getImageUri(): Uri? {
        return _currentImageUri.value
    }

    fun setImageUri(uri: Uri?) {
        _currentImageUri.value = uri
    }

    fun postRice(
        photo: MultipartBody.Part,
    ) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = newsRepository.postRice(photo)
            _scanResponse.value = result
            _isLoading.value = false
        }
    }

}
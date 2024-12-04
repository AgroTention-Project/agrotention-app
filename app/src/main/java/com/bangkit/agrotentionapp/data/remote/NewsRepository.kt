package com.bangkit.agrotentionapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.agrotentionapp.data.remote.response.DataItem
import com.bangkit.agrotentionapp.data.remote.retrofit.ApiService


class NewsRepository private constructor(private val apiService: ApiService) {

    fun fetchNews(): LiveData<Result<List<DataItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getNews()

            val filteredNews = response.data.filter {
                it.publisher != null && it.link != null
            }

            emit(Result.Success(filteredNews))
        } catch (e: Exception) {
            Log.d(TAG, "NewsRepository: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }


    companion object {
        const val TAG = "NewsRepository"

        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,

            ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService)
            }.also { instance = it }
    }

}
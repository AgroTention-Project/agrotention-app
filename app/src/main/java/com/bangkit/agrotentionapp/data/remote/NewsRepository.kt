package com.bangkit.agrotentionapp.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkit.agrotentionapp.data.remote.response.DataItem
import com.bangkit.agrotentionapp.data.remote.response.DiseasesResponse
import com.bangkit.agrotentionapp.data.remote.response.ScanResponse
import com.bangkit.agrotentionapp.data.remote.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException


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

    suspend fun postRice(
        photo: MultipartBody.Part,
    ): Result<ScanResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.postRice(photo)

                Result.Success(response)
            } catch (e: IOException) {
                Result.Error("Network error: ${e.message}")
            } catch (e: HttpException) {
                Result.Error("HTTP error: ${e.message}")
            } catch (e: Exception) {
                Result.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    suspend fun getDiseases(
        slug: String
    ): Result<DiseasesResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDiseases(slug)

                Result.Success(response)
            } catch (e: IOException) {
                Result.Error("Network error: ${e.message}")
            } catch (e: HttpException) {
                Result.Error("HTTP error: ${e.message}")
            } catch (e: Exception) {
                Result.Error("An unexpected error occurred: ${e.message}")
            }
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
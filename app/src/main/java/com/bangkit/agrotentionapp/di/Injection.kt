package com.bangkit.agrotentionapp.di

import android.content.Context
import com.bangkit.agrotentionapp.data.remote.NewsRepository
import com.bangkit.agrotentionapp.data.remote.retrofit.ApiConfig

object Injection {

    fun provideNewsRepository(context: Context): NewsRepository {
        val apiService = ApiConfig.getApiService()
        return NewsRepository.getInstance(apiService)
    }

}
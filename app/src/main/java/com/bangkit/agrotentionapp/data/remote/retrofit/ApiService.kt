package com.bangkit.agrotentionapp.data.remote.retrofit

import com.bangkit.agrotentionapp.data.remote.response.NewsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    suspend fun getNews(): NewsResponse
}
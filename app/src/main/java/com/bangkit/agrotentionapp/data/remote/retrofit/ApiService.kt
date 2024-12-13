package com.bangkit.agrotentionapp.data.remote.retrofit

import com.bangkit.agrotentionapp.data.remote.response.DiseasesResponse
import com.bangkit.agrotentionapp.data.remote.response.NewsResponse
import com.bangkit.agrotentionapp.data.remote.response.ScanResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("news")
    suspend fun getNews(): NewsResponse


    @Multipart
    @POST("scanner/?plant=rice")
    suspend fun postRice(
        @Part photo: MultipartBody.Part,
    ): ScanResponse

    @GET("diseases/{disease_slug}")
    suspend fun getDiseases (
        @Path("disease_slug") disease_slug: String
    ) : DiseasesResponse

}
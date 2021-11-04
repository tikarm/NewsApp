package com.example.newsapp.data.remote

import com.example.newsapp.domain.model.FullResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("search")
    suspend fun getNews(@Query("api-key") apiKey: String): FullResponse
}
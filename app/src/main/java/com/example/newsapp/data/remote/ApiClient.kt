package com.example.newsapp.data.remote

import com.example.newsapp.domain.model.FullResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

const val API_KEY = "38cdfe03-b912-4a9e-859a-ec8a3fa0bc1a"

class ApiClient @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getNews(): FullResponse {
        return apiService.getNews(API_KEY)
    }
}
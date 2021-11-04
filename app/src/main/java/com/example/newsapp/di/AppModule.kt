package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.ApiClient
import com.example.newsapp.data.remote.ApiService
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.NewsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val API_KEY = "38cdfe03-b912-4a9e-859a-ec8a3fa0bc1a"
const val BASE_URL = "https://content.guardianapis.com/"

@Module
object AppModule {

    @Provides
    @Singleton
    fun providesApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesApiClient(api: ApiService): ApiClient {
        return ApiClient(api)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(apiClient: ApiClient, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(apiClient, newsDao)
    }
}
package com.example.newsapp.di

import androidx.room.Room
import com.example.newsapp.MyApplication
import com.example.newsapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(application: MyApplication) {

    private var newsApplication = application
    private lateinit var newsDatabase: AppDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        newsDatabase = Room.databaseBuilder(
            newsApplication.applicationContext,
            AppDatabase::class.java,
            "news_database"
        )
            .build()
        return newsDatabase
    }

    @Singleton
    @Provides
    fun providesNewsDAO(newsDatabase: AppDatabase) = newsDatabase.newsDao()
}
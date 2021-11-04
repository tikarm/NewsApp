package com.example.newsapp.domain

import androidx.lifecycle.LiveData
import com.example.newsapp.domain.model.News

interface NewsRepository {
    suspend fun getNewsFromRemote()

    fun getNewsFromLocal(): LiveData<List<News>?>

    suspend fun insertListNews(newsList: List<News>?)

    suspend fun insertNews(news: News)

    suspend fun setNewsItemAsFavorite(isFavorite: Boolean, id: String)

    fun getFavoriteNews(): LiveData<List<News>?>
}
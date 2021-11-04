package com.example.newsapp.data.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.remote.ApiClient
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.News
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val apiClient: ApiClient, private val newsDao: NewsDao) :
    NewsRepository {

    override suspend fun getNewsFromRemote() {
        val result = apiClient.getNews().response
        insertListNews(result?.news)
    }

    override fun getNewsFromLocal(): LiveData<List<News>?> {
        return newsDao.getAll()
    }

    override suspend fun insertListNews(newsList: List<News>?) {
        newsDao.insertAll(newsList)
    }

    override suspend fun insertNews(news: News) {
        newsDao.insert(news)
    }

    override suspend fun setNewsItemAsFavorite(isFavorite: Boolean, id: String) {
        newsDao.update(isFavorite, id)
    }

    override fun getFavoriteNews(): LiveData<List<News>?> {
        return newsDao.loadFavorites()
    }
}
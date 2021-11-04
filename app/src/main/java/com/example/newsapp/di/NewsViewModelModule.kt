package com.example.newsapp.di

import com.example.newsapp.domain.use_case.GetNewsFromLocalUseCase
import com.example.newsapp.domain.use_case.GetNewsFromRemoteUseCase
import com.example.newsapp.domain.use_case.SetNewsAsFavoriteUseCase
import com.example.newsapp.ui.news.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NewsViewModelModule {

    @Provides
    @Singleton
    fun providesNewsViewModelFactory(
        getNewsFromRemoteUseCase: GetNewsFromRemoteUseCase,
        getNewsFromLocalUseCase: GetNewsFromLocalUseCase,
        setNewsAsFavoriteUseCase: SetNewsAsFavoriteUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            getNewsFromRemoteUseCase,
            getNewsFromLocalUseCase,
            setNewsAsFavoriteUseCase
        )
    }
}
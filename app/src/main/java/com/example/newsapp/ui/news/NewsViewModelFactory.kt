package com.example.newsapp.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.use_case.GetNewsFromLocalUseCase
import com.example.newsapp.domain.use_case.GetNewsFromRemoteUseCase
import com.example.newsapp.domain.use_case.SetNewsAsFavoriteUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsViewModelFactory @Inject constructor(
    private var getNewsFromRemoteUseCase: GetNewsFromRemoteUseCase,
    private var getNewsFromLocalUseCase: GetNewsFromLocalUseCase,
    private var setNewsAsFavoriteUseCase: SetNewsAsFavoriteUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(
                getNewsFromRemoteUseCase,
                getNewsFromLocalUseCase,
                setNewsAsFavoriteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
package com.example.newsapp.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.use_case.GetFavoritesUseCase
import com.example.newsapp.domain.use_case.SetNewsAsFavoriteUseCase
import javax.inject.Inject

class FavoritesViewModelFactory @Inject constructor(
    private var getFavoritesUseCase: GetFavoritesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            return FavoritesViewModel(
                getFavoritesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
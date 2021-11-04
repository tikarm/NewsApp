package com.example.newsapp.di

import com.example.newsapp.domain.use_case.GetFavoritesUseCase
import com.example.newsapp.ui.favorites.FavoritesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoritesViewModelModule {
    @Provides
    @Singleton
    fun providesFavoritesViewModelFactory(
        getFavoritesUseCase: GetFavoritesUseCase
    ): FavoritesViewModelFactory {
        return FavoritesViewModelFactory(
            getFavoritesUseCase
        )
    }
}
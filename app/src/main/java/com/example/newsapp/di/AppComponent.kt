package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.ui.main.MainActivity
import com.example.newsapp.ui.favorites.FavoritesFragment
import com.example.newsapp.ui.news.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NewsViewModelModule::class, FavoritesViewModelModule::class, AppModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, databaseModule: DatabaseModule): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: NewsFragment)
    fun inject(fragment: FavoritesFragment)
}
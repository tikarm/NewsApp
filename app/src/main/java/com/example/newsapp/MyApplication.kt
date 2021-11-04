package com.example.newsapp

import android.app.Application
import com.example.newsapp.di.AppComponent
import com.example.newsapp.di.DaggerAppComponent
import com.example.newsapp.di.DatabaseModule

class MyApplication : Application() {
        val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext, DatabaseModule(this))
    }
}
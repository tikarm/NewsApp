package com.example.newsapp.domain.use_case

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.common.COULDNT_GET_DATA
import com.example.newsapp.common.NO_FAVORITES
import com.example.newsapp.common.Resource
import com.example.newsapp.common.UNEXPECTED_ERROR
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

//    operator fun invoke(): Flow<Resource<LiveData<List<News>?>>> = flow {
//        try {
//            emit(Resource.Loading())
//            val favorites = newsRepository.getFavoriteNews()
//            Log.e("AAA", "invoke: " + favorites.value?.size)
//            if (favorites.value == null) {
//                emit(Resource.Error(NO_FAVORITES))
//            } else {
//                emit(Resource.Success(favorites))
//            }
//        } catch (e: IOException) {
//            emit(Resource.Error(COULDNT_GET_DATA))
//        } catch (e: Exception) {
//            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR))
//        }
//    }
//
    operator fun invoke(): LiveData<List<News>?> {
        return newsRepository.getFavoriteNews()
    }

}
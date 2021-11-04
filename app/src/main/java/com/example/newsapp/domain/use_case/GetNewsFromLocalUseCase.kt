package com.example.newsapp.domain.use_case

import androidx.lifecycle.LiveData
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsFromLocalUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): LiveData<List<News>?> {
        return newsRepository.getNewsFromLocal()
    }
}
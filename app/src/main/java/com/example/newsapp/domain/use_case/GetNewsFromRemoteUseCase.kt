package com.example.newsapp.domain.use_case

import com.example.newsapp.common.Resource
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsFromRemoteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            newsRepository.getNewsFromRemote()
            emit(Resource.Success("success"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection"))
        }
    }
}
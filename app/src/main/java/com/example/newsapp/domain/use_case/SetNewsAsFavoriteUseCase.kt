package com.example.newsapp.domain.use_case

import com.example.newsapp.domain.NewsRepository
import javax.inject.Inject

class SetNewsAsFavoriteUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(isFavorite: Boolean, id: String) {
        return newsRepository.setNewsItemAsFavorite(isFavorite, id)
    }
}
package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.ListNewsUseCase

class ListNewsUseCaseImpl(private val newsRepository: NewsRepository) : ListNewsUseCase {
    override suspend fun getNewsList(): List<News> {
        return newsRepository.getAllNews()
    }
}
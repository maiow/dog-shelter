package com.redpine.home.domain.usecase.impl

import com.redpine.core.model.card.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.ListNewsUseCase

class ListNewsUseCaseImpl(private val newsRepository: NewsRepository) : ListNewsUseCase {
    override suspend fun getNewsList(): List<News> {
        return newsRepository.getNewsList()
    }
}
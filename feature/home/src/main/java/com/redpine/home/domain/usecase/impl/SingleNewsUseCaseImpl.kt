package com.redpine.home.domain.usecase.impl

import com.redpine.core.model.card.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.SingleNewsUseCase

class SingleNewsUseCaseImpl(private val newsRepository: NewsRepository) : SingleNewsUseCase {

    override suspend fun getNewsById(newsId: Int): News {
        return newsRepository.getSingleNews(newsId)
    }
}
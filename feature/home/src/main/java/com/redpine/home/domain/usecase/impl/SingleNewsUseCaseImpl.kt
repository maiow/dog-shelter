package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.SingleNewsUseCase
import javax.inject.Inject

class SingleNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : SingleNewsUseCase {

    override suspend fun getNewsById(newsId: Int): News {
        return newsRepository.getSingleNews(newsId)
    }
}
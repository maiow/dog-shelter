package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.ListNewsUseCase
import javax.inject.Inject

class ListNewsUseCaseImpl @Inject constructor (
    private val newsRepository: NewsRepository
) : ListNewsUseCase {
    override suspend fun getNewsList(): List<News> {
        return newsRepository.getNewsList()
    }
}
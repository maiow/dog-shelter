package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.News

interface ListNewsUseCase {

    suspend fun getNewsList():List<News>
}
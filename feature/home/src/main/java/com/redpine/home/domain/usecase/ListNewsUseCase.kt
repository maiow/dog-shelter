package com.redpine.home.domain.usecase

import com.redpine.core.model.card.News

interface ListNewsUseCase {

    suspend fun getNewsList():List<News>
}
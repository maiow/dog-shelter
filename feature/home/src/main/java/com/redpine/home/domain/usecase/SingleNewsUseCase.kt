package com.redpine.home.domain.usecase

import com.redpine.core.model.card.News

interface SingleNewsUseCase {

    suspend fun getNewsById(newsId:Int):News
}
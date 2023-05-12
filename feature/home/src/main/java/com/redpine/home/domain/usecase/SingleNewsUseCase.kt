package com.redpine.home.domain.usecase

import com.redpine.core.domain.model.News

interface SingleNewsUseCase {

    suspend fun getNewsById(newsId:Int): News
}
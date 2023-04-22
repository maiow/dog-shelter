package com.redpine.home.domain.repository

import com.redpine.core.model.card.News

interface NewsRepository {
    suspend fun getNewsList(): List<News>
    suspend fun getSingleNews(id: Int): News
}

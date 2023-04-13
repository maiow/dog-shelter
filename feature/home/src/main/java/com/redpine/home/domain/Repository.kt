package com.redpine.home.domain

import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.domain.model.homeScreen.HomeScreen

interface Repository {

    suspend fun getItems(): List<HomeScreen>
    suspend fun getNewsList(): List<News>

//    suspend fun addToFavorites(item: Item)
}
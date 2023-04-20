package com.redpine.home.domain

import com.redpine.core.model.Response
import com.redpine.core.model.card.Dog
import com.redpine.home.domain.model.homeScreen.HomeScreen

interface Repository {

    suspend fun getItems(): List<HomeScreen>
    suspend fun getNewDogs(): Response
    suspend fun getRecentSeenDogs(count: Int): List<Dog>
    suspend fun getNewsList(): Response
    suspend fun getSingleNews(id: Int): Response

//    suspend fun addToFavorites(item: Item)
}
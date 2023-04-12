package com.redpine.home.data

import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid
import kotlin.random.Random

class RepositoryImpl:Repository {

//    override suspend fun addToFavorites(item: Item) {}

    override suspend fun getItems(): List<HomeScreen> {
        val listNewDog = mutableListOf<Item>()
        val listRecentSeenDog = mutableListOf<Item>()
        val listNews = mutableListOf<Item>()
        for (i in 1..10) {
            listNewDog.add(
                Dog(
                    i - 1,
                    "number $i",
                    "age ${i + 5} years",
                    "Новая Собака",
                    Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
            listRecentSeenDog.add(
                Dog(
                    i - 1,
                    "number $i",
                    "age ${i + 5} years",
                    "Какая-то Собака",
                    Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
            listNews.add(News(i, "title $i", "some string $i"))
        }
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }
}
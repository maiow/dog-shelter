package com.redpine.home.data

import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.News
import com.redpine.home.Data
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid
import kotlin.random.Random

class RepositoryImpl : Repository {

//    override suspend fun addToFavorites(item: Item) {}

    override suspend fun getItems(): List<Grid> {
        val listNewDog = getNewDogs(10)
        val listRecentSeenDog = getRecentSeenDogs(10)
        val listNews = getNewsList(10)
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val listNewDog = mutableListOf<Dog>()
        for (i in 1..count) {
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
        }
        return listNewDog.toList()
    }

    override suspend fun getRecentSeenDogs(count: Int): List<Dog> {
        val listRecentSeenDog = mutableListOf<Dog>()
        for (i in 1..count) {
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
        }
        return listRecentSeenDog.toList()
    }

    override suspend fun getNewsList(count: Int): List<News> {
        val listNews = mutableListOf<News>()
        for (i in 1..count) {
            listNews.add(News(i, "title $i", "some string $i",
                Data.images.shuffled().first().url))
        }
        return listNews.toList()
    }

    override suspend fun getSingleNews(id: Int): News =
        News(id, "Новостей нет", "Лень рисовать пока что",
            Data.images.shuffled().first().url)
}
package com.redpine.home.domain.usecase.impl

import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.R
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.HomeScreenUseCase
import kotlin.random.Random

class HomeScreenUseCaseImpl(
    private val dogsRepository: DogsRepository,
    private val newsRepository: NewsRepository
) : HomeScreenUseCase {

    override suspend fun getHomeScreenItems(newCount: Int, storyCount: Int): List<Grid> {
//        val listNewDog = dogsRepository.getNewDogs(newCount)
//        val listRecentSeenDog = dogsRepository.getRecentSeenDogs(storyCount)
//        val listNews = newsRepository.getNewsList()
        val listNewDog = mutableListOf<Item>()
        val listRecentSeenDog = mutableListOf<Item>()
        val listNews = mutableListOf<Item>()
        for(i in 1..10){
            listNewDog.add( Dog(id = i-1, name = "number $i", age =  "age ${i+5} years", isNew = Random.nextBoolean(),
                isFavorite = Random.nextBoolean())
            )
            listRecentSeenDog.add(  Dog(id = i-1, name = "number $i", age =  "age ${i+5} years", isNew = Random.nextBoolean(),
                isFavorite = Random.nextBoolean())
            )
            listNews.add(  News(id = i, title = "title $i", body = "some string $i"))
        }
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }

}
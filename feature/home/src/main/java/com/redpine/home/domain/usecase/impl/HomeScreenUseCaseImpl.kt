package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.home.R
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.model.grid.VerticalGrid
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.HomeScreenUseCase
import javax.inject.Inject

class HomeScreenUseCaseImpl @Inject constructor (
    private val dogsRepository: DogsRepository,
    private val newsRepository: NewsRepository
) : HomeScreenUseCase {

    override suspend fun getHomeScreenItems(newCount: Int, seenCount: Int): List<Grid> {
        val listNewDog = dogsRepository.getNewDogs(newCount)
        val listRecentSeenDog = dogsRepository.getRecentSeenDogs(seenCount)
        val listNews = newsRepository.getNewsList()

        return listOf(
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.New, list = listNewDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }
    override suspend fun getAllDogs(): List<Dog> = dogsRepository.getAllDogs()
}
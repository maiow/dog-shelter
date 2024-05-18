package com.redpine.home.domain.usecase

import com.redpine.home.domain.model.grid.Grid

interface HomeScreenUseCase {

    suspend fun getHomeScreenItems(
        newCount: Int = NEW_DOGS_COUNT,
        seenCount: Int = SEEN_DOGS_COUNT,
        newsCount: Int = NEWS_COUNT
    ): List<Grid>

    companion object {
        const val NEW_DOGS_COUNT = 10
        const val SEEN_DOGS_COUNT = 10
        const val NEWS_COUNT = 3
    }
}
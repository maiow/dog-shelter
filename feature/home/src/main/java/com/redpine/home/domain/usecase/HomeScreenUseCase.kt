package com.redpine.home.domain.usecase

import com.redpine.home.domain.model.grid.Grid

interface HomeScreenUseCase {

    suspend fun getHomeScreenItems(
        newCount: Int = NEW_DOGS_COUNT,
        seenCount: Int = SEEN_DOGS_COUNT
    ): List<Grid>

    private companion object {
        const val NEW_DOGS_COUNT = 12
        const val SEEN_DOGS_COUNT = 10
    }
}
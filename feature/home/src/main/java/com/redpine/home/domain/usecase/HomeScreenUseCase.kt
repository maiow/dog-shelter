package com.redpine.home.domain.usecase

import com.redpine.home.domain.model.grid.Grid

interface HomeScreenUseCase {

    suspend fun getHomeScreenItems(
        newCount: Int = NEW_COUNT,
        storyCount: Int = STORY_COUNT
    ): List<Grid>

    private companion object {
        const val NEW_COUNT = 10
        const val STORY_COUNT = 10
    }
}
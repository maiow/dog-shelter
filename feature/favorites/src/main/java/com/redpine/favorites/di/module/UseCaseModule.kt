package com.redpine.favorites.di.module

import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.impl.DislikeUseCaseImpl
import com.redpine.favorites.domain.usecase.impl.FavoritesUseCaseImpl
import com.redpine.favorites.domain.usecase.impl.SearchUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesDislikeUseCase(favoritesRepository: FavoritesRepository) =
        DislikeUseCaseImpl(favoritesRepository)

    @Provides
    fun providesSearchUseCase(favoritesRepository: FavoritesRepository) =
        SearchUseCaseImpl(favoritesRepository)

    @Provides
    fun providesFavoritesUseCase(favoritesRepository: FavoritesRepository) =
        FavoritesUseCaseImpl(favoritesRepository)
}
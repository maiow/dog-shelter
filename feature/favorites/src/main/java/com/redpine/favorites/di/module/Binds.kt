package com.redpine.favorites.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.favorites.FavoritesViewModelFactory
import com.redpine.favorites.data.FavoritesRepositoryImpl
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.DislikeUseCase
import com.redpine.favorites.domain.usecase.FavoritesUseCase
import com.redpine.favorites.domain.usecase.SearchUseCase
import com.redpine.favorites.domain.usecase.impl.DislikeUseCaseImpl
import com.redpine.favorites.domain.usecase.impl.FavoritesUseCaseImpl
import com.redpine.favorites.domain.usecase.impl.SearchUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface Binds {

    @Binds
    fun bindsFavoritesRepository(repositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: FavoritesViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindsDislikeUseCase(dislikeUseCase: DislikeUseCaseImpl): DislikeUseCase

    @Binds
    fun bindsSearchUseCase(searchUseCase: SearchUseCaseImpl): SearchUseCase

    @Binds
    fun bindsFavoritesUseCase(favoritesUseCase: FavoritesUseCaseImpl): FavoritesUseCase
}
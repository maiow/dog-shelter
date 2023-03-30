package com.redpine.favorites.di.module

import com.redpine.favorites.data.FavoritesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesFavoritesRepository() = FavoritesRepositoryImpl()
}
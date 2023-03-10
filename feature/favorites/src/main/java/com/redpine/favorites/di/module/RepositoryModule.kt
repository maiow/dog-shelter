package com.redpine.favorites.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.favorites.FavoritesViewModelFactory
import com.redpine.favorites.data.FavoritesRepositoryImpl
import com.redpine.favorites.domain.FavoritesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesFavoritesRepository() = FavoritesRepositoryImpl()
}

@Module
interface Binds {

    @dagger.Binds
    fun bindsFavoritesRepository(repositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

    @dagger.Binds
    fun bindsViewModelFactory(viewModelFactory: FavoritesViewModelFactory): ViewModelProvider.Factory


}
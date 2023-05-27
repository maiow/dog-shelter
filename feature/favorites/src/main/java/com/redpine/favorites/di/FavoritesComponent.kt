package com.redpine.favorites.di

import androidx.lifecycle.ViewModelProvider
import com.redpine.core.base.DiComponent
import com.redpine.favorites.di.module.Binds
import com.redpine.favorites.di.module.RepositoryModule
import com.redpine.favorites.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        FavoritesDependencies::class
    ], modules = [
        RepositoryModule::class,
        Binds::class,
        UseCaseModule::class
    ]
)
@Singleton
interface FavoritesComponent : DiComponent {
    override val viewModelFactory: ViewModelProvider.Factory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: FavoritesDependencies): Builder

        fun build(): FavoritesComponent
    }
}
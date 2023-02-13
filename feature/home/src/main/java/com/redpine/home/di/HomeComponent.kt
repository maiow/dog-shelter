package com.redpine.home.di

import com.redpine.api.Api
import com.redpine.home.ViewModelFactory
import com.redpine.home.di.module.Binds
import com.redpine.home.di.module.RepositoryModule
import com.redpine.home.presentation.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [
        HomeDependencies::class
    ], modules = [
        RepositoryModule::class,
        Binds::class
    ]
)
@Singleton
interface HomeComponent {
    val viewModelFactory: ViewModelFactory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: HomeDependencies): Builder

        fun build(): HomeComponent
    }
}
package com.redpine.home.di

import com.redpine.api.Api
import com.redpine.home.ViewModelFactory
import com.redpine.home.presentation.HomeFragment
import dagger.Component

@Component(
    dependencies = [
        HomeDependencies::class
    ]
)
interface HomeComponent {
    val viewModelFactory: ViewModelFactory

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: HomeDependencies): Builder

        fun build(): HomeComponent
    }
}
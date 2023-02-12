package com.redpine.home.di

import com.redpine.api.Api
import com.redpine.home.HomeFragment
import dagger.Component

@Component(
    dependencies = [
        HomeDependencies::class
    ]
)
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)

    val api: Api

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: HomeDependencies): Builder

        fun build(): HomeComponent
    }
}
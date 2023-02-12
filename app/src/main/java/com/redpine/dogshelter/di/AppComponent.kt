package com.redpine.dogshelter.di

import com.redpine.api.Api
import com.redpine.home.di.HomeComponent
import com.redpine.home.di.HomeDependencies
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        Module::class
    ]
)
@Singleton
interface AppComponent : HomeDependencies {

    override var api: Api
}
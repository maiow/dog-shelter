package com.redpine.dogshelter.di

import com.redpine.api.Api
import com.redpine.chats.di.ChatsDependencies
import com.redpine.favorites.di.FavoritesDependencies
import com.redpine.home.di.HomeDependencies
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        Module::class
    ]
)
@Singleton
interface AppComponent : ChatsDependencies, FavoritesDependencies, HomeDependencies {

    override var api: Api
}
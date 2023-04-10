package com.redpine.dogshelter.di

import android.app.Application
import android.content.Context
import com.redpine.api.Api
import com.redpine.chats.di.ChatsDependencies
import com.redpine.core.domain.TokenProvider
import com.redpine.favorites.di.FavoritesDependencies
import com.redpine.home.di.deps.HomeDependencies
import dagger.BindsInstance
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
    override val tokenProvider: TokenProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}
package com.redpine.dogshelter.di

import android.content.Context
import com.redpine.api.Api
import com.redpine.chats.di.ChatsDependencies
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider
import com.redpine.dogshelter.presentation.MainActivity
import com.redpine.favorites.di.FavoritesDependencies
import com.redpine.home.di.component.HomeComponent
import com.redpine.home.di.module.HomeModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        Module::class,
        HomeModule::class
    ]
)
@Singleton
interface AppComponent : HomeComponent, ChatsDependencies, FavoritesDependencies {

    fun inject(mainActivity: MainActivity)

    override var api: Api
     val tokenProvider: TokenProvider
     val onBoardingPrefs: OnBoardingPrefs

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}
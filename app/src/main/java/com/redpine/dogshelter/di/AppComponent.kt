package com.redpine.dogshelter.di

import android.content.Context
import com.redpine.api.Api
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider
import com.redpine.dogshelter.presentation.MainActivity
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
interface AppComponent : FavoritesDependencies, HomeDependencies {

    fun inject(mainActivity: MainActivity)

    override var api: Api
    override val tokenProvider: TokenProvider
    override val onBoardingPrefs: OnBoardingPrefs

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}
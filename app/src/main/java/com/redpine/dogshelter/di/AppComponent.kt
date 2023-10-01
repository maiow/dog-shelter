package com.redpine.dogshelter.di

import android.content.Context
import com.redpine.api.Api
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider
import com.redpine.dogshelter.presentation.MainActivity
import com.redpine.favorites.di.FavoritesDependencies
import com.redpine.home.di.deps.HomeDependencies
import com.redpine.home.domain.utils.CalendarInstance
import com.redpine.profile.di.ProfileDependencies
import dagger.BindsInstance
import dagger.Component
import ru.sr.auth.di.deps.AuthDependencies
import javax.inject.Singleton

@Component(
    modules = [
        Module::class
    ]
)
@Singleton
interface AppComponent : ProfileDependencies, FavoritesDependencies, HomeDependencies,AuthDependencies {

    fun inject(mainActivity: MainActivity)

    override var api: Api
    override val tokenProvider: TokenProvider
    override val onBoardingPrefs: OnBoardingPrefs
    override val authDialogPrefs: AuthDialogPrefs
    override val calendarInstance: CalendarInstance
    override val context: Context

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}
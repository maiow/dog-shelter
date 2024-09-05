package com.redpine.dogshelter.di

import android.content.Context
import com.redpine.core.data.AuthDialogPrefsImpl
import com.redpine.core.data.OnBoardingPrefsImpl
import com.redpine.core.data.TokenProviderImpl
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider
import com.redpine.dogshelter.presentation.MainViewModelFactory
import com.redpine.home.domain.utils.CalendarInstance
import dagger.Provides
import javax.inject.Singleton

@dagger.Module
object Module {

    @Provides
    @Singleton
    fun providesTokenProvider(context: Context): TokenProvider = TokenProviderImpl(context)

    @Provides
    @Singleton
    fun providesOnBoardingPrefs(context: Context): OnBoardingPrefs = OnBoardingPrefsImpl(context)

    @Provides
    @Singleton
    fun providesMainViewModelFactory(onBoardingPrefs: OnBoardingPrefs) =
        MainViewModelFactory(onBoardingPrefs)

    @Provides
    @Singleton
    fun providesAuthDialogPrefs(context: Context): AuthDialogPrefs = AuthDialogPrefsImpl(context)

    @Provides
    @Singleton
    fun providesCalendarInstance(): CalendarInstance = CalendarInstance()
}

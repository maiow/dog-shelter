package com.redpine.home.di.module

import com.redpine.home.data.OnboardingRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOnboardingRepository() = OnboardingRepositoryImpl()
}

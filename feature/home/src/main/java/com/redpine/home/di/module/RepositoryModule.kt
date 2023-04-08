package com.redpine.home.di.module

import com.redpine.home.data.OnboardingRepositoryImpl
import com.redpine.home.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOnboardingRepository() = OnboardingRepositoryImpl()

    @Provides
    @Singleton
    fun provideRepository() = RepositoryImpl()
}

package com.redpine.home.di.module

import com.redpine.home.data.OnboardingRepositoryImpl
import com.redpine.home.domain.OnboardingRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesOnboardingRepository() = OnboardingRepositoryImpl()
}

@Module
interface Binds {

    @dagger.Binds
    fun bindsOnboardingRepository(repositoryImpl: OnboardingRepositoryImpl): OnboardingRepository
}
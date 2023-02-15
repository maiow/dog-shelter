package com.redpine.home.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.home.ViewModelFactory
import com.redpine.home.data.AuthRepositoryImpl
import com.redpine.home.data.OnboardingRepositoryImpl
import com.redpine.home.domain.AuthRepository
import com.redpine.home.domain.OnboardingRepository
import dagger.Module

@Module
interface Binds {

    @dagger.Binds
    fun bindsOnboardingRepository(repositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

    @dagger.Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @dagger.Binds
    fun bindsAuthRepository(repo: AuthRepositoryImpl): AuthRepository
}
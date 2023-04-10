package com.redpine.home.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.home.ViewModelFactory
import com.redpine.home.data.repository.AuthenticationRepositoryImpl
import com.redpine.home.data.repository.OnboardingRepositoryImpl
import com.redpine.home.domain.repository.OnBoardingRepository
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.impl.AuthUseCaseImpl
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.home.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Module

@Module
interface Binds {

    @dagger.Binds
    fun bindsOnBoardingRepository(repositoryImpl: OnboardingRepositoryImpl): OnBoardingRepository

    @dagger.Binds
    fun bindsAuthenticationRepository(repositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @dagger.Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @dagger.Binds
    fun bindsAuthUseCase(useCaseImpl: AuthUseCaseImpl): AuthUseCase

    @dagger.Binds
    fun bindsRegistrationUseCase(useCaseImpl: RegistrationUseCaseImpl): RegistrationUseCase

    @dagger.Binds
    fun bindsResetPasswordUseCase(useCaseImpl:ResetPasswordUseCaseImpl): ResetPasswordUseCase
}
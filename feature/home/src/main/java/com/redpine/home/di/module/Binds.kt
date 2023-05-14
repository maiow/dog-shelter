package com.redpine.home.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.home.HomeViewModelFactory
import com.redpine.home.data.repository.AuthenticationRepositoryImpl
import com.redpine.home.data.repository.DogsRepositoryImpl
import com.redpine.home.data.repository.NewsRepositoryImpl
import com.redpine.home.data.repository.OnboardingRepositoryImpl
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.repository.OnboardingRepository
import com.redpine.home.domain.usecase.AuthTokenUseCase
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.ListNewsUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.domain.usecase.SingleNewsUseCase
import com.redpine.home.domain.usecase.impl.AuthTokenUseCaseImpl
import com.redpine.home.domain.usecase.impl.AuthUseCaseImpl
import com.redpine.home.domain.usecase.impl.HomeScreenUseCaseImpl
import com.redpine.home.domain.usecase.impl.ListNewsUseCaseImpl
import com.redpine.home.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.home.domain.usecase.impl.ResetPasswordUseCaseImpl
import com.redpine.home.domain.usecase.impl.SingleNewsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface Binds {

    @Binds
    fun bindsOnboardingRepository(repositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    fun bindsAuthenticationRepository(repositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindsHomeViewModelFactory(homeViewModelFactory: HomeViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindsAuthUseCase(useCaseImpl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindsRegistrationUseCase(useCaseImpl: RegistrationUseCaseImpl): RegistrationUseCase

    @Binds
    fun bindsResetPasswordUseCase(useCaseImpl:ResetPasswordUseCaseImpl): ResetPasswordUseCase

    @Binds
    fun bindsNewsRepository(repositoryImpl: NewsRepositoryImpl): NewsRepository

    @Binds
    fun bindsDogsRepository(repositoryImpl: DogsRepositoryImpl): DogsRepository

    @Binds
    fun bindsAuthTokenUseCase(useCase: AuthTokenUseCaseImpl): AuthTokenUseCase

    @Binds
    fun bindsListNewsUseCase(useCase: ListNewsUseCaseImpl): ListNewsUseCase

    @Binds
    fun bindsHomeScreenUseCase(useCase: HomeScreenUseCaseImpl): HomeScreenUseCase

    @Binds
    fun bindsSingleNewsUseCase(useCase: SingleNewsUseCaseImpl): SingleNewsUseCase

}
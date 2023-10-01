package ru.sr.auth.di.module

import androidx.lifecycle.ViewModelProvider
import ru.sr.auth.domain.usecase.AuthTokenUseCase
import ru.sr.auth.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import ru.sr.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import ru.sr.auth.domain.usecase.impl.RegistrationUseCaseImpl
import ru.sr.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import ru.sr.auth.data.AuthenticationRepositoryImpl
import ru.sr.auth.domain.AuthenticationRepository
import ru.sr.auth.presentation.ViewModelFactory
import ru.sr.auth.domain.usecase.impl.AuthUseCaseImpl

@Module
interface Binds {


    @Binds
    fun bindsAuthenticationRepository(repositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindsAuthUseCase(useCaseImpl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun bindsRegistrationUseCase(useCaseImpl: RegistrationUseCaseImpl): RegistrationUseCase

    @Binds
    fun bindsResetPasswordUseCase(useCaseImpl: ResetPasswordUseCaseImpl): ResetPasswordUseCase


    @Binds
    fun bindsAuthTokenUseCase(useCase: AuthTokenUseCaseImpl): AuthTokenUseCase
}
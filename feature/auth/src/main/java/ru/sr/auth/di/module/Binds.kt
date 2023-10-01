package ru.sr.auth.di.module

import androidx.lifecycle.ViewModelProvider
import ru.sr.auth.domain.usecase.AuthTokenUseCase
import ru.sr.auth.domain.usecase.AuthEmailAndPasswordUseCase
import ru.sr.auth.domain.usecase.RegistrationUseCase
import ru.sr.auth.domain.usecase.ResetPasswordUseCase
import ru.sr.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import ru.sr.auth.domain.usecase.impl.RegistrationUseCaseImpl
import ru.sr.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import ru.sr.auth.data.repository.AuthGoogleRepositoryImpl
import ru.sr.auth.data.repository.AuthenticationEmailAndPasswordRepositoryImpl
import ru.sr.auth.data.googleAuth.GoogleAuthApiImpl
import ru.sr.auth.domain.AuthGoogleRepository
import ru.sr.auth.domain.AuthenticationEmailAndPasswordRepository
import ru.sr.auth.domain.GoogleAuthApi
import ru.sr.auth.presentation.ViewModelFactory
import ru.sr.auth.domain.usecase.impl.AuthEmailAndPasswordUseCaseImpl

@Module
interface Binds {


    @Binds
    fun bindsAuthenticationRepository(repositoryImpl: AuthenticationEmailAndPasswordRepositoryImpl): AuthenticationEmailAndPasswordRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindsAuthUseCase(useCaseImpl: AuthEmailAndPasswordUseCaseImpl): AuthEmailAndPasswordUseCase

    @Binds
    fun bindsRegistrationUseCase(useCaseImpl: RegistrationUseCaseImpl): RegistrationUseCase

    @Binds
    fun bindsResetPasswordUseCase(useCaseImpl: ResetPasswordUseCaseImpl): ResetPasswordUseCase


    @Binds
    fun bindsAuthTokenUseCase(useCase: AuthTokenUseCaseImpl): AuthTokenUseCase

    @Binds
    fun bindsGoogleAuthApi(api: GoogleAuthApiImpl): GoogleAuthApi

    @Binds
    fun bindsAuthGoogleRepository(repository: AuthGoogleRepositoryImpl): AuthGoogleRepository


}
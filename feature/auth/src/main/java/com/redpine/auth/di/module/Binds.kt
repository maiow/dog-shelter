package com.redpine.auth.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.auth.data.googleAuth.GoogleAuthApiImpl
import com.redpine.auth.data.repository.AuthGoogleRepositoryImpl
import com.redpine.auth.data.repository.AuthenticationEmailAndPasswordRepositoryImpl
import com.redpine.auth.domain.AuthGoogleRepository
import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.GoogleAuthApi
import com.redpine.auth.domain.usecase.AuthEmailAndPasswordUseCase
import com.redpine.auth.domain.usecase.AuthTokenUseCase
import com.redpine.auth.domain.usecase.CheckIfNewUserUseCase
import com.redpine.auth.domain.usecase.RegistrationUseCase
import com.redpine.auth.domain.usecase.ResetPasswordUseCase
import com.redpine.auth.domain.usecase.impl.AuthEmailAndPasswordUseCaseImpl
import com.redpine.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import com.redpine.auth.domain.usecase.impl.CheckIfNewUserUseCaseImpl
import com.redpine.auth.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import com.redpine.auth.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

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

    @Binds
    fun bindsCheckIfNewUserUseCase(useCaseImpl: CheckIfNewUserUseCaseImpl): CheckIfNewUserUseCase

}

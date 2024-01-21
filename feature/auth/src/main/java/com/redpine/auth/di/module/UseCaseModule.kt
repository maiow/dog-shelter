package com.redpine.auth.di.module

import com.redpine.auth.domain.AuthenticationEmailAndPasswordRepository
import com.redpine.auth.domain.usecase.impl.AuthEmailAndPasswordUseCaseImpl
import com.redpine.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import com.redpine.auth.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import com.redpine.core.domain.TokenProvider
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesAuthUseCase(authenticationRepository: AuthenticationEmailAndPasswordRepository) =
        AuthEmailAndPasswordUseCaseImpl(authenticationRepository)

    @Provides
    fun providesRegistrationUseCase(authenticationRepository: AuthenticationEmailAndPasswordRepository) =
        RegistrationUseCaseImpl(authenticationRepository)

    @Provides
    fun providesResetPasswordUseCase(authenticationRepository: AuthenticationEmailAndPasswordRepository) =
        ResetPasswordUseCaseImpl(authenticationRepository)

    @Provides
    fun providesAuthTokenUseCase(tokenProvider: TokenProvider) =
        AuthTokenUseCaseImpl(tokenProvider)
}
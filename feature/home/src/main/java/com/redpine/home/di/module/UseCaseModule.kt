package com.redpine.home.di.module

import com.redpine.home.domain.usecase.impl.AuthUseCaseImpl
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.home.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesAuthUseCase(authenticationRepository: AuthenticationRepository) =
        AuthUseCaseImpl(authenticationRepository)

    @Provides
    fun providesRegistrationUseCase(authenticationRepository: AuthenticationRepository) =
       RegistrationUseCaseImpl(authenticationRepository)

    @Provides
    fun providesResetPasswordUseCase(authenticationRepository: AuthenticationRepository) =
        ResetPasswordUseCaseImpl(authenticationRepository)
}
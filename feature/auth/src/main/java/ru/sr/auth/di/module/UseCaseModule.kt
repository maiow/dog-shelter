package ru.sr.auth.di.module

import com.redpine.core.domain.TokenProvider
import ru.sr.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import ru.sr.auth.domain.usecase.impl.RegistrationUseCaseImpl
import ru.sr.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Module
import dagger.Provides
import ru.sr.auth.domain.AuthenticationRepository
import ru.sr.auth.domain.usecase.impl.AuthUseCaseImpl

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

    @Provides
    fun providesAuthTokenUseCase(tokenProvider: TokenProvider) =
        AuthTokenUseCaseImpl(tokenProvider)
}
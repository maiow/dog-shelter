package ru.sr.auth.di.module

import com.redpine.core.domain.TokenProvider
import ru.sr.auth.domain.usecase.impl.AuthTokenUseCaseImpl
import ru.sr.auth.domain.usecase.impl.RegistrationUseCaseImpl
import ru.sr.auth.domain.usecase.impl.ResetPasswordUseCaseImpl
import dagger.Module
import dagger.Provides
import ru.sr.auth.domain.AuthenticationEmailAndPasswordRepository
import ru.sr.auth.domain.usecase.impl.AuthEmailAndPasswordUseCaseImpl

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
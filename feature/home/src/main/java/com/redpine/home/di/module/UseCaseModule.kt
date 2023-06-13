package com.redpine.home.di.module

import com.redpine.core.domain.TokenProvider
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.impl.AuthTokenUseCaseImpl
import com.redpine.home.domain.usecase.impl.AuthUseCaseImpl
import com.redpine.home.domain.usecase.impl.DogInfoUseCaseImpl
import com.redpine.home.domain.usecase.impl.FilterUseCaseImpl
import com.redpine.home.domain.usecase.impl.HomeScreenUseCaseImpl
import com.redpine.home.domain.usecase.impl.LikeUseCaseImpl
import com.redpine.home.domain.usecase.impl.ListNewsUseCaseImpl
import com.redpine.home.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.home.domain.usecase.impl.ResetPasswordUseCaseImpl
import com.redpine.home.domain.usecase.impl.SearchUseCaseImpl
import com.redpine.home.domain.usecase.impl.SeenListUseCaseImpl
import com.redpine.home.domain.usecase.impl.SingleNewsUseCaseImpl
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

    @Provides
    fun providesAuthTokenUseCase(tokenProvider: TokenProvider) =
        AuthTokenUseCaseImpl(tokenProvider)

    @Provides
    fun providesHomeUseCase(dogsRepository: DogsRepository, newsRepository: NewsRepository) =
        HomeScreenUseCaseImpl(dogsRepository, newsRepository)

    @Provides
    fun providesListNewsUseCase(newsRepository: NewsRepository) =
        ListNewsUseCaseImpl(newsRepository)

    @Provides
    fun providesSingleNewsUseCase(newsRepository: NewsRepository) =
        SingleNewsUseCaseImpl(newsRepository)

    @Provides
    fun providesLikeUseCase(dogsRepository: DogsRepository) =
        LikeUseCaseImpl(dogsRepository)

    @Provides
    fun providesFilterUseCase(dogsRepository: DogsRepository) =
        FilterUseCaseImpl(dogsRepository)

    @Provides
    fun providesSearchUseCase(dogsRepository: DogsRepository) =
        SearchUseCaseImpl(dogsRepository)

    @Provides
    fun providesDogInfoUseCase(dogsRepository: DogsRepository) =
        DogInfoUseCaseImpl(dogsRepository)

    @Provides
    fun providesSeenListUseCase(dogsRepository: DogsRepository) =
        SeenListUseCaseImpl(dogsRepository)
}
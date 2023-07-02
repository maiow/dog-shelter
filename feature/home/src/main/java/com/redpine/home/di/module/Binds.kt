package com.redpine.home.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.home.ViewModelFactory
import com.redpine.home.data.repository.AuthenticationRepositoryImpl
import com.redpine.home.data.repository.DogsRepositoryImpl
import com.redpine.home.data.repository.FiltrationRepositoryImpl
import com.redpine.home.data.repository.NewsRepositoryImpl
import com.redpine.home.data.repository.OnboardingRepositoryImpl
import com.redpine.home.domain.repository.AuthenticationRepository
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.repository.OnboardingRepository
import com.redpine.home.domain.usecase.AuthTokenUseCase
import com.redpine.home.domain.usecase.AuthUseCase
import com.redpine.home.domain.usecase.DogInfoUseCase
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.usecase.FilteredDogsUseCase
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import com.redpine.home.domain.usecase.ListNewsUseCase
import com.redpine.home.domain.usecase.RegistrationUseCase
import com.redpine.home.domain.usecase.ResetPasswordUseCase
import com.redpine.home.domain.usecase.SearchUseCase
import com.redpine.home.domain.usecase.SeenListUseCase
import com.redpine.home.domain.usecase.SingleNewsUseCase
import com.redpine.home.domain.usecase.impl.AuthTokenUseCaseImpl
import com.redpine.home.domain.usecase.impl.AuthUseCaseImpl
import com.redpine.home.domain.usecase.impl.DogInfoUseCaseImpl
import com.redpine.home.domain.usecase.impl.FilterUseCaseImpl
import com.redpine.home.domain.usecase.impl.FilteredDogsUseCaseImpl
import com.redpine.home.domain.usecase.impl.HomeScreenUseCaseImpl
import com.redpine.home.domain.usecase.impl.LikeUseCaseImpl
import com.redpine.home.domain.usecase.impl.ListNewsUseCaseImpl
import com.redpine.home.domain.usecase.impl.RegistrationUseCaseImpl
import com.redpine.home.domain.usecase.impl.ResetPasswordUseCaseImpl
import com.redpine.home.domain.usecase.impl.SearchUseCaseImpl
import com.redpine.home.domain.usecase.impl.SeenListUseCaseImpl
import com.redpine.home.domain.usecase.impl.SingleNewsUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface Binds {

    @Binds
    fun bindsOnboardingRepository(repositoryImpl: OnboardingRepositoryImpl): OnboardingRepository

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

    @Binds
    fun bindsLikeUseCase(likeUseCase: LikeUseCaseImpl): LikeUseCase

    @Binds
    fun bindsFilterUseCase(filterUseCase: FilterUseCaseImpl): FilterUseCase

    @Binds
    fun bindsSearchUseCase(searchUseCase: SearchUseCaseImpl): SearchUseCase

    @Binds
    fun bindsDogInfoUseCase(dogInfoUseCase: DogInfoUseCaseImpl): DogInfoUseCase

    @Binds
    fun bindsSeenListUseCase(seenListUseCase: SeenListUseCaseImpl): SeenListUseCase

    @Binds
    @Singleton
    fun bindsFilteredDogsUseCase(filteredDogsUseCase: FilteredDogsUseCaseImpl): FilteredDogsUseCase

    @Binds
    @Singleton
    fun bindsFiltrationRepository(filtrationRepository: FiltrationRepositoryImpl): FiltrationRepository
}
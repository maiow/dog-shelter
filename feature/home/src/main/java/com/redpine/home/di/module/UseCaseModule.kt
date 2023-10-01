package com.redpine.home.di.module

import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.impl.DogInfoUseCaseImpl
import com.redpine.home.domain.usecase.impl.FilterUseCaseImpl
import com.redpine.home.domain.usecase.impl.FilteredDogsUseCaseImpl
import com.redpine.home.domain.usecase.impl.HomeScreenUseCaseImpl
import com.redpine.home.domain.usecase.impl.LikeUseCaseImpl
import com.redpine.home.domain.usecase.impl.ListNewsUseCaseImpl
import com.redpine.home.domain.usecase.impl.SearchUseCaseImpl
import com.redpine.home.domain.usecase.impl.SeenListUseCaseImpl
import com.redpine.home.domain.usecase.impl.SingleNewsUseCaseImpl
import com.redpine.home.domain.utils.CalendarInstance
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

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
    fun providesFilterUseCase(filtration: FiltrationRepository) =
        FilterUseCaseImpl(filtration)

    @Provides
    fun providesSearchUseCase(dogsRepository: DogsRepository) =
        SearchUseCaseImpl(dogsRepository)

    @Provides
    fun providesDogInfoUseCase(dogsRepository: DogsRepository) =
        DogInfoUseCaseImpl(dogsRepository)

    @Provides
    fun providesSeenListUseCase(dogsRepository: DogsRepository) =
        SeenListUseCaseImpl(dogsRepository)

    @Provides
    fun providesFilteredDogsUseCase(
        dogsRepository: DogsRepository,
        filtration: FiltrationRepository,
        calendarInstance: CalendarInstance
    ) = FilteredDogsUseCaseImpl(dogsRepository, filtration, calendarInstance)
}
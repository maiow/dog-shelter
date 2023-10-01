package com.redpine.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.home.domain.repository.OnboardingRepository
import com.redpine.home.domain.usecase.DogInfoUseCase
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.usecase.FilteredDogsUseCase
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import com.redpine.home.domain.usecase.ListNewsUseCase
import com.redpine.home.domain.usecase.SearchUseCase
import com.redpine.home.domain.usecase.SeenListUseCase
import com.redpine.home.domain.usecase.SingleNewsUseCase
import com.redpine.home.presentation.filter.FilterViewModel
import com.redpine.home.presentation.found.DogsFoundViewModel
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.news.NewsListViewModel
import com.redpine.home.presentation.news.SingleNewsViewModel
import com.redpine.home.presentation.onboarding.OnboardingViewModel
import com.redpine.home.presentation.pets_card.PetsCardViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(
    private val onboardingRepository: OnboardingRepository,
    private val onBoardingPrefs: OnBoardingPrefs,
    private val homeScreenUseCase: HomeScreenUseCase,
    private val listNewsUseCase: ListNewsUseCase,
    private val singleNewsUseCase: SingleNewsUseCase,
    private val likeUseCase: LikeUseCase,
    private val filterUseCase: FilterUseCase,
    private val searchUseCase: SearchUseCase,
    private val dogInfoUseCase: DogInfoUseCase,
    private val seenListUseCase: SeenListUseCase,
    private val filteredDogsUseCase: FilteredDogsUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(homeScreenUseCase, likeUseCase, searchUseCase) as T
        OnboardingViewModel::class.java -> OnboardingViewModel(onboardingRepository, onBoardingPrefs) as T
        FilterViewModel::class.java -> FilterViewModel(filterUseCase) as T
        DogsFoundViewModel::class.java -> DogsFoundViewModel(likeUseCase, filteredDogsUseCase, filterUseCase) as T
        PetsCardViewModel::class.java -> PetsCardViewModel(dogInfoUseCase, seenListUseCase, likeUseCase) as T
        NewsListViewModel::class.java -> NewsListViewModel(listNewsUseCase) as T
        SingleNewsViewModel::class.java -> SingleNewsViewModel(singleNewsUseCase) as T
        else -> throw IllegalAccessError("error create viewModel")
    }


}



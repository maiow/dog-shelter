package com.redpine.profile.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.profile.ProfileViewModelFactory
import com.redpine.profile.data.ProfileRepositoryImpl
import com.redpine.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module

@Module
interface Binds {

    @Binds
    fun bindsProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ProfileViewModelFactory): ViewModelProvider.Factory

}
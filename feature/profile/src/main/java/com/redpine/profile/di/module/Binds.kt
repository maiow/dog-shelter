package com.redpine.profile.di.module

import androidx.lifecycle.ViewModelProvider
import com.redpine.profile.ProfileViewModelFactory
import com.redpine.profile.data.ProfileRepositoryImpl
import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.domain.usecase.DeleteAccountUseCase
import com.redpine.profile.domain.usecase.LogoutUseCase
import com.redpine.profile.domain.usecase.ProfileInfoUseCase
import com.redpine.profile.domain.usecase.impl.DeleteAccountUseCaseImpl
import com.redpine.profile.domain.usecase.impl.LogoutUseCaseImpl
import com.redpine.profile.domain.usecase.impl.ProfileInfoUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface Binds {

    @Binds
    fun bindsProfileRepository(repositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ProfileViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindsDeleteAccountUseCase(deleteAccountUseCase: DeleteAccountUseCaseImpl): DeleteAccountUseCase

    @Binds
    fun bindsLogoutUseCase(logoutUseCase: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    fun bindsProfileInfoUseCase(profileInfoUseCase: ProfileInfoUseCaseImpl): ProfileInfoUseCase
}
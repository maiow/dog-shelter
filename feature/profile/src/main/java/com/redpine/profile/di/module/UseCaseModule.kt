package com.redpine.profile.di.module

import com.redpine.profile.domain.ProfileRepository
import com.redpine.profile.domain.usecase.impl.DeleteAccountUseCaseImpl
import com.redpine.profile.domain.usecase.impl.LogoutUseCaseImpl
import com.redpine.profile.domain.usecase.impl.ProfileInfoUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesDeleteAccountUseCase(profileRepository: ProfileRepository) =
        DeleteAccountUseCaseImpl(profileRepository)

    @Provides
    fun providesLogoutUseCase(profileRepository: ProfileRepository) =
        LogoutUseCaseImpl(profileRepository)

    @Provides
    fun providesProfileInfoUseCase(profileRepository: ProfileRepository) =
        ProfileInfoUseCaseImpl(profileRepository)
}
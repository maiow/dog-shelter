package com.redpine.home.di.deps

import com.redpine.api.Api
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider

interface HomeDependencies {
 val api:Api
 val tokenProvider:TokenProvider
 val onBoardingPrefs: OnBoardingPrefs
}
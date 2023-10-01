package ru.sr.auth.di.deps

import com.redpine.api.Api
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider

interface AuthDependencies {
    val api: Api
    val tokenProvider: TokenProvider
    val onBoardingPrefs: OnBoardingPrefs
    val authDialogPrefs: AuthDialogPrefs
}
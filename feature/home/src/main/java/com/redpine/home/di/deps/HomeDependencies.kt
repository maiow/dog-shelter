package com.redpine.home.di.deps

import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.OnBoardingPrefs
import com.redpine.core.domain.TokenProvider
import com.redpine.home.domain.utils.CalendarInstance

interface HomeDependencies {
    val tokenProvider: TokenProvider
    val onBoardingPrefs: OnBoardingPrefs
    val authDialogPrefs: AuthDialogPrefs
    val calendarInstance: CalendarInstance
}
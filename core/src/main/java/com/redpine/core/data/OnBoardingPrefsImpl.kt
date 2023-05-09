package com.redpine.core.data

import android.content.Context
import com.redpine.core.domain.OnBoardingPrefs

class OnBoardingPrefsImpl(context: Context): OnBoardingPrefs {

    private val preferenceToken =
        context.getSharedPreferences(ONBOARDING_PREFS_NAME, Context.MODE_PRIVATE)

    override fun rememberOnBoardingIsShown() {
        preferenceToken.edit().putBoolean(ONBOARDING_IS_SHOWN, true).apply()
    }

    override fun clearToken() {
        preferenceToken.edit().clear().apply()
    }

    override fun isShown(): Boolean {
        return preferenceToken.getBoolean(ONBOARDING_IS_SHOWN, false)
    }

    companion object {
        private const val ONBOARDING_PREFS_NAME = "on_boarding_prefs"
        private const val ONBOARDING_IS_SHOWN = "on_boarding_is_shown"
    }

}
package com.redpine.core.domain

interface OnBoardingPrefs{

    fun rememberOnBoardingIsShown()

    fun clearToken()

    fun isShown(): Boolean
}
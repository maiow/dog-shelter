package com.redpine.core.domain

interface AuthDialogPrefs {

    fun rememberAuthDialogIsShown()

    fun clearToken()

    fun isShown(): Boolean
}
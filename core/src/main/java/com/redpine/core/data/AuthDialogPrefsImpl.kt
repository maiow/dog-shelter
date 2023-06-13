package com.redpine.core.data

import android.content.Context
import com.redpine.core.domain.AuthDialogPrefs

class AuthDialogPrefsImpl(context: Context): AuthDialogPrefs {

    private val preferenceToken =
        context.getSharedPreferences(AUTH_DIALOG_PREFS_NAME, Context.MODE_PRIVATE)

    override fun rememberAuthDialogIsShown() {
        preferenceToken.edit().putBoolean(AUTH_DIALOG_IS_SHOWN, true).apply()
    }

    override fun clearToken() {
        preferenceToken.edit().clear().apply()
    }

    override fun isShown(): Boolean {
        return preferenceToken.getBoolean(AUTH_DIALOG_IS_SHOWN, false)
    }

    companion object {
        private const val AUTH_DIALOG_PREFS_NAME = "auth_dialog_prefs"
        private const val AUTH_DIALOG_IS_SHOWN = "auth_dialog_is_shown"
    }
}
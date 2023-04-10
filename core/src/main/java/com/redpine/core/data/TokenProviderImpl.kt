package com.redpine.core.data

import android.content.Context
import com.redpine.core.domain.TokenProvider

class TokenProviderImpl(private val context: Context): TokenProvider {

    private val preferenceToken =
        context.getSharedPreferences(TOKEN_SHARED_NAME, Context.MODE_PRIVATE)

    override fun putToken(token: String) {
        preferenceToken.edit().putString(TOKEN, token).apply()
    }

    override fun clearToken() {
        preferenceToken.edit().clear().apply()
    }

    override fun getToken(): String? =
        preferenceToken.getString(TOKEN, null)


    override fun tokenContain(): Boolean {
        return getToken() != null
    }

    companion object {
        private const val TOKEN_SHARED_NAME = "Pref_Token"
        private const val TOKEN = "token"
    }

}
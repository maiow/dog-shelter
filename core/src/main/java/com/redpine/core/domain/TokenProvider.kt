package com.redpine.core.domain

interface TokenProvider{

    fun putToken(token: String)

    fun clearToken()

    fun getToken(): String?

    fun tokenContain(): Boolean
}
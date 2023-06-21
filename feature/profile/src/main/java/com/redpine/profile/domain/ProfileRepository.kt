package com.redpine.profile.domain

interface ProfileRepository {

    suspend fun isUserAuthorized():Boolean

    suspend fun getEmail():String

    suspend fun deleteAccount(): Boolean

    suspend fun reauthenticateUser(email: String, password: String): Boolean

    suspend fun logout(): Boolean

}
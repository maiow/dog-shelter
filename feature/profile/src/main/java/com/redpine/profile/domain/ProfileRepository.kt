package com.redpine.profile.domain

interface ProfileRepository {

    suspend fun isUserAuthorized():Boolean

    suspend fun getEmail():String

}
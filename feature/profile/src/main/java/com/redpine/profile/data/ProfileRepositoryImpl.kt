package com.redpine.profile.data

import com.google.firebase.auth.FirebaseAuth
import com.redpine.profile.domain.ProfileRepository

class ProfileRepositoryImpl() : ProfileRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


    /**нула не должно быть, до перехода на избранное должна быть проверка на то, авторизован ли юзер*/
    private fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun isUserAuthorized(): Boolean {
        return getUserId() != null
    }
}
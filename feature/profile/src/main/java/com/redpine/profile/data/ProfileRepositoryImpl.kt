package com.redpine.profile.data

import com.google.firebase.auth.FirebaseAuth
import com.redpine.profile.domain.ProfileRepository

class ProfileRepositoryImpl : ProfileRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun isUserAuthorized(): Boolean {
        return getUserId() != null
    }

    override suspend fun getEmail(): String = firebaseAuth.currentUser?.email ?: ""
}
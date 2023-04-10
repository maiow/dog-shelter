package com.redpine.home.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.redpine.home.domain.repository.AuthenticationRepository
import kotlinx.coroutines.tasks.await


class AuthenticationRepositoryImpl(
    private val fireBaseAuth: FirebaseAuth
    ) : AuthenticationRepository {

    override fun authEmail(email: String, password: String) =
        fireBaseAuth.signInWithEmailAndPassword(email, password)

    override fun getUser() = fireBaseAuth.currentUser

    override fun createUser(email: String, password: String) =
        fireBaseAuth.createUserWithEmailAndPassword(email, password)

    override fun userEmailVerified() =
        fireBaseAuth.currentUser?.isEmailVerified ?: false
}
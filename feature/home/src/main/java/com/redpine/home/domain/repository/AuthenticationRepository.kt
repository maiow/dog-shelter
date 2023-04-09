package com.redpine.home.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthenticationRepository {

    fun authEmail(email: String, password: String): Task<AuthResult>

    fun getUser(): FirebaseUser?

    fun createUser(email: String, password: String): Task<AuthResult>

    fun userEmailVerified(): Boolean
}


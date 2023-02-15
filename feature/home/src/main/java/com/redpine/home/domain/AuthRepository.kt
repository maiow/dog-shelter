package com.redpine.home.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun authEmail(email: String, password: String): Task<AuthResult>

    fun getUser(): FirebaseUser?

    fun createUser(email: String, password: String): Task<AuthResult>
}
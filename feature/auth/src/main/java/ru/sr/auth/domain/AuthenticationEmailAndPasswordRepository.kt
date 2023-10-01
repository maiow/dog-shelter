package ru.sr.auth.domain

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationEmailAndPasswordRepository {

    fun authEmail(email: String, password: String): Task<AuthResult>

    fun createUser(email: String, password: String): Task<AuthResult>

    fun resetPassword(email: String): Task<Void>

}


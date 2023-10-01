package ru.sr.auth.domain.usecase

interface AuthTokenUseCase {

    fun putToken(token:String)
}
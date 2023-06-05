package com.redpine.home.domain.repository

import com.redpine.core.domain.model.Dog

interface DogsRepository {
    suspend fun getDogImages(id: Int): List<String>
    suspend fun getNewDogs(count: Int): List<Dog>
    suspend fun getRecentSeenDogs(count: Int): List<Dog>
    suspend fun getDogInfo(id: Int): Dog
    suspend fun sendDogToSeenList(id: Int)
    suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean
    suspend fun filterDogs(
        minAge: String,
        maxAge: String,
        gender: String,
        size: String?,
        character: String): List<Dog>
    suspend fun getAllDogs(): List<Dog>
}
package com.redpine.home.domain.repository

import com.redpine.core.domain.model.Dog

interface DogsRepository {
    suspend fun getDogImages(id: Int): List<String>
    suspend fun getNewDogs(count: Int): List<Dog>
    suspend fun getRecentSeenDogs(count: Int): List<Dog>
    suspend fun getDogLikeInfo(id: Int): Boolean
    suspend fun sendDogToSeenList(id: Int)
    suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean
    suspend fun getAllDogs(): List<Dog>
    suspend fun searchDogByName(query: String): Dog
}
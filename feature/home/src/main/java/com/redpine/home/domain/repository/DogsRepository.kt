package com.redpine.home.domain.repository

import com.redpine.core.model.Response
import com.redpine.core.model.card.Dog

interface DogsRepository{
    suspend fun getDogImages(id: Int): List<String>
    suspend fun getNewDogs(count: Int): List<Dog>
    suspend fun getRecentSeenDogs(count: Int): List<Dog>
}
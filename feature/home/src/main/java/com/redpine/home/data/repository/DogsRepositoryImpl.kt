package com.redpine.home.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.toDog
import com.redpine.core.extensions.toDogList
import com.redpine.home.data.FilteredDogs
import com.redpine.home.domain.repository.DogsRepository
import kotlinx.coroutines.tasks.await

class DogsRepositoryImpl(private val database: DatabaseReference) : DogsRepository {

    private val fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val uid = fireBaseAuth.currentUser?.uid

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val dogsList = database
            .child(DOGS_NODE)
            .limitToLast(count)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }
        if (dogsList.isNotEmpty()) return dogsList.toDogList() else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getDogImages(id: Int): List<String> {
        val listOfImages = database
            .child(GALLERY_NODE)
            .child(GALLERY_NODE + id)
            .get()
            .await()
            .children.map { snapShot -> snapShot.value.toString() }
        if (listOfImages.isNotEmpty()) return listOfImages else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getRecentSeenDogs(count: Int): List<Dog> {
        val listRecentSeenDogsDto = mutableListOf<DogDto>()
        if (uid != null) {
            val listSeenIds = mutableSetOf<String>()
            database
                .child(SEEN_NODE)
                .child(uid)
                .limitToLast(count)
                .get()
                .await()
                .children.map { snapShot -> snapShot.value.let { listSeenIds.add(it.toString()) } }

            listSeenIds.forEach {
                listRecentSeenDogsDto.add(
                    database
                        .child(DOGS_NODE)
                        .child(DOGS_NODE_CHILD + it)
                        .get()
                        .await()
                        .getValue(DogDto::class.java) ?: DogDto()
                )
            }
        }
        return if (listRecentSeenDogsDto.isNotEmpty()) listRecentSeenDogsDto.toDogList()
        else emptyList()
    }

    override suspend fun getDogInfo(id: Int): Dog {
        val res = database
            .child(DOGS_NODE)
            .child(DOGS_NODE_CHILD + id)
            .get()
            .await()
            .getValue(DogDto::class.java) ?: DogDto()
        if (res.id != 0) return res.toDog() else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun sendDogToSeenList(id: Int) {
        if (uid != null) {
            database
                .child(SEEN_NODE)
                .child(uid)
                .push()
                .setValue(id)
                .await()
        }
    }

    override suspend fun makeLikeDislike(id: Int, isLike: Boolean): Boolean {
        if (uid == null) return false
        else
            if (isLike) {
                database
                    .child(LIKES_NODE)
                    .child(uid)
                    .child(id.toString())
                    .setValue(id)
                    .await()

            } else {
                database
                    .child(LIKES_NODE)
                    .child(uid)
                    .child(id.toString())
                    .removeValue()
                    .await()
            }
        return true
    }

    override suspend fun filterDogs(
        minAge: String,
        maxAge: String,
        gender: String,
        size: String?,
        character: String
    ): List<Dog> {

        /**фильтрация в бд по полу, если не указан Любой*/
        if (gender != "Любой" && gender != "Any") {
            val filteredByGenderDogsList = database.child(DOGS_NODE)
                .orderByChild("gender")
                .equalTo(gender)
                .get().await()
                .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }

            var filteredDogsList = mutableListOf<DogDto>()
            if (size != null) {
                filteredByGenderDogsList.forEach {
                    if (it.size == size) filteredDogsList.add(it)
                }
                if (filteredDogsList.size == 0) filteredDogsList =
                    emptyList<DogDto>().toMutableList()
            } else filteredDogsList = filteredByGenderDogsList.toMutableList()
            FilteredDogs.filteredDogsList = filteredDogsList.toDogList()
            return filteredDogsList.toDogList()
        } else {
            /**пол был указан Любой или Any, фильтрация в бд по размеру,
             * если был указан только один размер, а не несколько*/
            if (size != null && !size.contains(",")) {
                val filteredBySizeDogsList = database.child(DOGS_NODE)
                    .orderByChild("size")
                    .equalTo(size)
                    .get().await()
                    .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }

                var filteredDogsList = mutableListOf<DogDto>()
                filteredBySizeDogsList.forEach {
                    if (it.size == size) filteredDogsList.add(it)
                }
                if (filteredDogsList.size == 0) filteredDogsList =
                    emptyList<DogDto>().toMutableList()
                FilteredDogs.filteredDogsList = filteredDogsList.toDogList()
                return filteredDogsList.toDogList()
            } else {
                /**пол был указан Любой или Any И размер либо не указан, либо указано несколько,
                 * все собаки без фильтрации*/
                val dogsList = database
                    .child(DOGS_NODE)
                    .get().await()
                    .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }
                FilteredDogs.filteredDogsList = dogsList.toDogList()
                return dogsList.toDogList()
            }
        }
    }

    private companion object {
        const val DOGS_NODE = "dogs"
        const val DOGS_NODE_CHILD = "dog"
        const val GALLERY_NODE = "gallery"
        const val SEEN_NODE = "seen"
        const val LIKES_NODE = "likes"
    }
}
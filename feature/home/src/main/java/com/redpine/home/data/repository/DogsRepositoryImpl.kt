package com.redpine.home.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.toDog
import com.redpine.core.extensions.toDogList
import com.redpine.home.domain.repository.DogsRepository
import kotlinx.coroutines.tasks.await

class DogsRepositoryImpl(private val database: DatabaseReference) : DogsRepository {

    private val fireBaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val uid = fireBaseAuth.currentUser?.uid

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val dogsLists = database
            .child(DOGS_NODE)
            .limitToLast(count)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }
        if (dogsLists.isNotEmpty()) return dogsLists.toDogList() else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getDogImages(id: Int): List<String> {
        val listImage = database
            .child(GALLERY_NODE)
            .child(GALLERY_NODE + id)
            .get()
            .await()
            .children.map { snapShot -> snapShot.value.toString() }
        if (listImage.isNotEmpty()) return listImage else throw FirebaseBaseExceptionNullResponse()
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
            if (isLike){
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

    private companion object {
        const val DOGS_NODE = "dogs"
        const val DOGS_NODE_CHILD = "dog"
        const val GALLERY_NODE = "gallery"
        const val SEEN_NODE = "seen"
        const val LIKES_NODE = "likes"
    }
}
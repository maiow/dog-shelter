package com.redpine.favorites.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseSearchExceptionNullResponse
import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.toDog
import com.redpine.core.extensions.toFavoriteDogList
import com.redpine.favorites.domain.FavoritesRepository
import kotlinx.coroutines.tasks.await

class FavoritesRepositoryImpl(private val database: DatabaseReference) : FavoritesRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun isUserAuthorized(): Boolean = getUserId() != null

    override suspend fun getFavoriteDogs(): List<Dog> {
        val listFavoriteDogsDto = mutableListOf<DogDto>()
        val uid = getUserId()
        if (uid != null) {
            val listLikedIds = mutableSetOf<String>()
            database
                .child(LIKES_NODE)
                .child(uid)
                .get()
                .await()
                .children.map { snapShot -> snapShot.value.let { listLikedIds.add(it.toString()) } }

            listLikedIds.forEach {
                listFavoriteDogsDto.add(
                    database
                        .child(DOGS_NODE)
                        .child(DOGS_NODE_CHILD + it)
                        .get()
                        .await()
                        .getValue(DogDto::class.java) ?: DogDto()
                )
            }
        }
        return if (listFavoriteDogsDto.isNotEmpty()) listFavoriteDogsDto.toFavoriteDogList()
        else emptyList()
    }

    override suspend fun makeDislike(dogId: Int): Boolean {
        val uid = getUserId()
        if (uid == null) return false
        else
            database
                .child(LIKES_NODE)
                .child(uid)
                .child(dogId.toString())
                .removeValue()
                .await()
        return true
    }

    override suspend fun searchDogByName(query: String): Dog {
        val searchResult = database
            .child(DOGS_NODE)
            .orderByChild(NAME_NODE)
            .equalTo(query)
            .get()
            .await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }

        if (searchResult.isNotEmpty() && searchResult[0].id != 0)
            return searchResult[0].toDog()
        else throw FirebaseSearchExceptionNullResponse()
    }

    companion object {
        const val LIKES_NODE = "likes"
        const val DOGS_NODE = "dogs"
        const val DOGS_NODE_CHILD = "dog"
        const val NAME_NODE = "name"
    }
}
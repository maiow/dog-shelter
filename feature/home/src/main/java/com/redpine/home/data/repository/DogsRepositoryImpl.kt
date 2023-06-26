package com.redpine.home.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.base.FirebaseSearchExceptionNullResponse
import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.toDog
import com.redpine.core.extensions.toDogList
import com.redpine.home.data.FilteredDogs
import com.redpine.home.domain.repository.DogsRepository
import kotlinx.coroutines.tasks.await

class DogsRepositoryImpl(
    private val database: DatabaseReference,
    private val firebaseAuth: FirebaseAuth
) : DogsRepository {

    /** подумать, для какого экрана может быть нужно в случае нулл кидать ошибку и
     * во вью модели ее обрабатывать, и что именно показывать юзеру*/
    private fun getUserId(): String? = firebaseAuth.currentUser?.uid
    private var listFavoriteDogsIds = mutableSetOf<String>()

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val uid = getUserId()
        val dogsList = database.child(DOGS_NODE)
            .orderByChild(ID)
            .limitToLast(count)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }
            .ifEmpty { throw FirebaseBaseExceptionNullResponse() }
        return if (uid == null) {
            dogsList.toDogList().asReversed()
        } else {
            getFavoriteDogs(uid)
            dogsList.forEach { dog ->
                if (dog.id.toString() in listFavoriteDogsIds) dog.isFavorite = true
            }
            dogsList.toDogList().asReversed()
        }
    }

    private suspend fun getFavoriteDogs(uid: String) {
        listFavoriteDogsIds.clear()
        database
            .child(LIKES_NODE)
            .child(uid)
            .get()
            .await()
            .children.map { snapShot -> snapShot.value.let { listFavoriteDogsIds.add(it.toString()) } }
    }

    override suspend fun getDogImages(id: Int): List<String> {
        return database
            .child(GALLERY_NODE)
            .child(GALLERY_NODE + id)
            .get()
            .await()
            .children.map { snapShot -> snapShot.value.toString() }
            .ifEmpty { throw FirebaseBaseExceptionNullResponse() }
    }

    override suspend fun getRecentSeenDogs(count: Int): List<Dog> {
        val listRecentSeenDogsDto = mutableListOf<DogDto>()
        val uid = getUserId()
        //TODO: поменять на val uid = getUserId() ?: throw NullPointerException() с обработкой во
        // вью модели и показом чего-то на экране
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
        return if (listRecentSeenDogsDto.isNotEmpty()) {
            listRecentSeenDogsDto.forEach { dog ->
                if (dog.id.toString() in listFavoriteDogsIds) dog.isFavorite = true
            }
            listRecentSeenDogsDto.toDogList().asReversed()
        } else emptyList()
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
        val uid = getUserId()
        if (uid != null) {
            database
                .child(SEEN_NODE)
                .child(uid)
                .push()
                .setValue(id)
                .await()
        }
    }

    override suspend fun makeLikeDislike(dogId: Int, isLike: Boolean): Boolean {
        val uid = getUserId() ?: return false
        val databaseReference = database
            .child(LIKES_NODE)
            .child(uid)
            .child(dogId.toString())

        if (isLike) setFavorite(databaseReference, dogId)
        else removeFavorite(databaseReference)

        return true
    }

    private suspend fun setFavorite(reference: DatabaseReference, dogId: Int) =
        reference.setValue(dogId).await()

    private suspend fun removeFavorite(reference: DatabaseReference) =
        reference.removeValue().await()


    //TODO: добавить фильтрацию по остальным передаваемым параметрам + по всем не передаваемым сейчас
// в репозиторий чекбоксам
    override suspend fun filterDogs(
        minAge: String, maxAge: String, gender: String, size: String?, character: String,
    ): List<Dog> {
        var authorized = false
        val uid = getUserId()
        if (uid != null) {
            getFavoriteDogs(uid)
            authorized = true
        }
        /**фильтрация в бд по полу, если не указан Любой + добавление лайков*/
        if (gender != GENDER_ANY_RU && gender != GENDER_ANY_EN) {
            return filterDogsByGender(gender, size, authorized)
        } else {
            /**пол был указан Любой или Any, фильтрация в бд по размеру,
             * если был указан только один размер, а не несколько, + добавление лайков*/
            if (size != null && !size.contains(",")) {
                return filterDogsBySize(size, authorized)
            } else {
                /**пол был указан Любой или Any И размер либо не указан, либо указано несколько,
                 * все собаки без фильтрации + добавление лайков*/
                return getAllDogs()
            }
        }
    }

    private suspend fun filterDogsByGender(
        gender: String,
        size: String?,
        authorized: Boolean
    ): List<Dog> {
        val filteredByGenderDogsList = database.child(DOGS_NODE)
            .orderByChild(GENDER_NODE)
            .equalTo(gender)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }

        var filteredDogsList = mutableListOf<DogDto>()
        if (size != null) {
            filteredByGenderDogsList.forEach { dog ->
                if (dog.size == size) filteredDogsList.add(dog)
            }
        } else filteredDogsList = filteredByGenderDogsList.toMutableList()
        if (authorized) {
            filteredDogsList.forEach { dog ->
                if (dog.id.toString() in listFavoriteDogsIds) dog.isFavorite = true
            }
        }
        FilteredDogs.filteredDogsList = filteredDogsList.toDogList()
        return filteredDogsList.toDogList()
    }

    private suspend fun filterDogsBySize(size: String?, authorized: Boolean): List<Dog> {
        val filteredBySizeDogsList = database.child(DOGS_NODE)
            .orderByChild(SIZE_NODE)
            .equalTo(size)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }

        if (authorized) {
            filteredBySizeDogsList.forEach { dog ->
                if (dog.id.toString() in listFavoriteDogsIds) dog.isFavorite = true
            }
        }
        FilteredDogs.filteredDogsList = filteredBySizeDogsList.toDogList()
        return filteredBySizeDogsList.toDogList()
    }

    override suspend fun getAllDogs(): List<Dog> {
        val uid = getUserId()
        val dogsList = database
            .child(DOGS_NODE)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(DogDto::class.java) ?: DogDto() }
        if (uid != null) {
            getFavoriteDogs(uid)
            dogsList.forEach { dog ->
                if (dog.id.toString() in listFavoriteDogsIds) dog.isFavorite = true
            }
        }
        FilteredDogs.filteredDogsList = dogsList.toDogList()
        return dogsList.toDogList()
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

    private companion object {
        const val DOGS_NODE = "dogs"
        const val DOGS_NODE_CHILD = "dog"
        const val GALLERY_NODE = "gallery"
        const val SEEN_NODE = "seen"
        const val LIKES_NODE = "likes"
        const val GENDER_ANY_RU = "Любой"
        const val GENDER_ANY_EN = "Any"
        const val GENDER_NODE = "gender"
        const val SIZE_NODE = "size"
        const val NAME_NODE = "name"
        const val ID = "id"
    }
}
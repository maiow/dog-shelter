package com.redpine.home.data.repository

import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.model.card.Dog
import com.redpine.home.domain.repository.DogsRepository
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class DogsRepositoryImpl(private val database: DatabaseReference) : DogsRepository {

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val dogsList = database
            .child(DOGS_NODE)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(Dog::class.java) ?: Dog() }
        /// TODO: дополнить логику ограничением в count штук
        if (dogsList.isNotEmpty()) return dogsList else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getDogImages(id: Int): List<String> {
       val listImage = database
           .child(GALLERY_NODE)
           .child(GALLERY_NODE+id)
           .get()
           .await()
           .children.map { snapShot -> snapShot.value.toString() }
        if (listImage.isNotEmpty()) return listImage else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getRecentSeenDogs(count: Int): List<Dog> {
        val listRecentSeenDog = mutableListOf<Dog>()
        for (i in 1..count) {
            listRecentSeenDog.add(
                Dog(
                    "age ${i + 5} years", "active", "dark",
                    "89162223322",
                    "male", 45, i - 1,
                    "https://firebasestorage.googleapis.com/v0/b/dog-shelter-d6e3e.appspot.com/o/news%2FvRgs4P4iyEs.jpg?alt=media&token=f3d1ddf2-c4a3-4102-a31b-cea6e567ba15",
                    "number $i",
                    "small",
                    "Nothing to say, that's a cool dog",
                    Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
        }
        return listRecentSeenDog.toList()
    }

    private companion object {
        const val DOGS_NODE = "dogs"
        const val GALLERY_NODE = "news"
    }
}
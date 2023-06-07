package com.redpine.favorites.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.data.DogDto
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.toFavoriteDogList
import com.redpine.favorites.domain.FavoritesRepository
import kotlinx.coroutines.tasks.await

class FavoritesRepositoryImpl(private val database: DatabaseReference) : FavoritesRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var connectedRef: DatabaseReference? = null
    private var valueEventListener: ValueEventListener? = null
    private var connected: Boolean = true

    /**нула не должно быть, до перехода на избранное должна быть проверка на то, авторизован ли юзер*/
    private fun getUserId(): String? = firebaseAuth.currentUser?.uid

    override suspend fun isUserAuthorized(): Boolean {
        return getUserId() != null
    }

    private fun checkConnection() {
        connectedRef = database.child(".info/connected")
        valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                connected = snapshot.getValue(Boolean::class.java) ?: false
                if (connected) {
                    Log.d("BRED", "connected")
                } else {
                    Log.d("BRED", "not connected")
                    removeConnectionListener()
                    /**здесь выброс ошибки крашит, придумать другой вариант передачи инфо об
                     * отсутствии сети во вью модель*/
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("BRED", "Listener was cancelled")
            }
        }
        connectedRef!!.addValueEventListener(valueEventListener as ValueEventListener)
    }

    override suspend fun getFavoriteDogs(): List<Dog> {
        val listFavoriteDogsDto = mutableListOf<DogDto>()
        val uid = getUserId()
        if (uid != null) {
            checkConnection()
            if (connected) {
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
            } else {
                /**не срабатывает, переписать*/
                Log.w("BRED", "not connected from getFavoriteDogs")
                throw FirebaseBaseExceptionNullResponse()
            }
        }
        return if (listFavoriteDogsDto.isNotEmpty()) listFavoriteDogsDto.toFavoriteDogList()
        else emptyList()
    }

    private fun removeConnectionListener() =
        connectedRef!!.removeEventListener(valueEventListener!!)

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

    companion object {
        const val LIKES_NODE = "likes"
        const val DOGS_NODE = "dogs"
        const val DOGS_NODE_CHILD = "dog"
    }
}
package com.redpine.home.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.redpine.core.model.Response
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class RepositoryImpl : Repository {

    //    override suspend fun addToFavorites(item: Item) {}
    private val database: DatabaseReference by lazy {
        Firebase
            .database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/")
            .reference
    }

    override suspend fun getItems(): List<HomeScreen> {
        val listNewDog = getNewDogs().dogsList as List<Item>
        val listRecentSeenDog = getRecentSeenDogs(10)
        val listNews = getNewsList().newsList as List<Item>
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }

    override suspend fun getNewDogs(): Response {
            val response = Response()
            try {
                response.dogsList = database.child("dogs").get().await()
                    .children.map { snapShot -> snapShot.getValue(Dog::class.java)!! }
            } catch (exception: Exception) {
                response.exception = exception
            }
            return response
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

    //snapshot.getChildrenCount()

    override suspend fun getNewsList(): Response {
        val response = Response()
        try {
            response.newsList = database.child("news").get().await()
                .children.map { snapShot -> snapShot.getValue(News::class.java)!! }
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }

    override suspend fun getSingleNews(id: Int): Response {
        val response = Response()
        val singleNews = database.child("news").child("news$id")
        try {
            response.news = singleNews.get().await().getValue(News::class.java)!!
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
    }
}
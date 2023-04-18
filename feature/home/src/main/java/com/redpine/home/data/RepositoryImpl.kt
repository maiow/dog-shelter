package com.redpine.home.data

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.redpine.core.model.Response
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.News
import com.redpine.home.Data
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class RepositoryImpl : Repository {

    //    override suspend fun addToFavorites(item: Item) {}
//    private val database: DatabaseReference by lazy {
//        Firebase
//            .database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/")
//            .reference
//    }
    //  private var _newsItem = News()

    // private val newsItem get() = _newsItem
    //private lateinit var newsItem: NewsOne

    override suspend fun getItems(): List<HomeScreen> {
        val listNewDog = getNewDogs(10)
        val listRecentSeenDog = getRecentSeenDogs(10)
        val listNews = getNewsList(10)
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }

    override suspend fun getNewDogs(count: Int): List<Dog> {
        val listNewDog = mutableListOf<Dog>()
        for (i in 1..count) {
            listNewDog.add(
                Dog(
                    i - 1,
                    "number $i",
                    "age ${i + 5} years",
                    "Новая Собака",
                    Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
        }
        return listNewDog.toList()
    }

    override suspend fun getRecentSeenDogs(count: Int): List<Dog> {
        val listRecentSeenDog = mutableListOf<Dog>()
        for (i in 1..count) {
            listRecentSeenDog.add(
                Dog(
                    i - 1,
                    "number $i",
                    "age ${i + 5} years",
                    "Какая-то Собака",
                    Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
        }
        return listRecentSeenDog.toList()
    }

    override suspend fun getNewsList(count: Int): List<News> {
        val listNews = mutableListOf<News>()
        for (i in 1..count) {
            listNews.add(
                News(
                    "some string $i", i,
                    Data.images.shuffled().first().url, false, "title $i"
                )
            )
        }
        return listNews.toList()
    }

    override suspend fun getSingleNews(id: Int): Response {
        val response = Response()

        val database =
            Firebase.database("https://dog-shelter-d6e3e-default-rtdb.europe-west1.firebasedatabase.app/").reference

        val singleNews = database.child("news").child("news" + "$id")
        try {
            response.news = singleNews.get().await().getValue(News::class.java)!!
        } catch (exception: Exception) {
            response.exception = exception
        }
        return response
        /**для листа
        try {
        response.news = database.child("news").get().await().children.map { snapShot ->
        snapShot.getValue(NewsOne::class.java)!!
        }
        } catch (exception: Exception) {
        response.exception = exception
        }
        return response
         */
    }
}
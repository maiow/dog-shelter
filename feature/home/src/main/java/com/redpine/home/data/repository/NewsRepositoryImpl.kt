package com.redpine.home.data.repository

import com.google.firebase.database.DatabaseReference
import com.redpine.core.base.FirebaseBaseExceptionNullResponse
import com.redpine.core.domain.model.News
import com.redpine.home.domain.repository.NewsRepository
import kotlinx.coroutines.tasks.await

class NewsRepositoryImpl(private val database: DatabaseReference) : NewsRepository {

    override suspend fun getNewsList(count: Int): List<News> {
        val newsList = database
            .child(NEWS_NODE)
            .orderByChild(NEWS_ID)
            .limitToLast(count)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(News::class.java) ?: News() }
        if (newsList.isNotEmpty()) return newsList.reversed() else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getSingleNews(id: Int): News {
        val news = database
            .child(NEWS_NODE)
            .child(NEWS_NODE + id)
            .get().await()
            .getValue(News::class.java)
        if (news != null) return news else throw FirebaseBaseExceptionNullResponse()
    }

    override suspend fun getAllNews(): List<News> {
        val newsList = database
            .child(NEWS_NODE)
            .orderByChild(NEWS_ID)
            .get().await()
            .children.map { snapShot -> snapShot.getValue(News::class.java) ?: News() }
        if (newsList.isNotEmpty()) return newsList.reversed() else throw FirebaseBaseExceptionNullResponse()
    }

    private companion object {
        const val NEWS_NODE = "news"
        const val NEWS_ID = "id"
    }
}
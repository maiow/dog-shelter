package com.redpine.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.redpine.api.Api
import com.redpine.core.model.card.*
import com.redpine.home.R
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {


    init {
        createHomeScreen()
    }

    fun createHomeScreen(): List<HomeScreen> {
        val listNewDog = mutableListOf<Item>()
        val listRecentSeenDog = mutableListOf<Item>()
        val listNews = mutableListOf<Item>()
        for(i in 1..10){
           listNewDog.add( Dog(i-1,"number $i", "age ${i+5} years","Новая Собака", Random.nextBoolean(),Random.nextBoolean()))
           listRecentSeenDog.add(  Dog(i-1,"number $i", "age ${i+5} years","Какая-то Собака", Random.nextBoolean(),Random.nextBoolean()))
            listNews.add(  News(i, "title $i", "some string $i"))
        }
        return listOf(
            HorizontalGrid(titleId = R.string.New, list = listNewDog, spanCount = 1),
            HorizontalGrid(titleId = R.string.Recent_seen, list = listRecentSeenDog),
            VerticalGrid(titleId = R.string.News, list = listNews, spanCount = 1),
        )
    }

}






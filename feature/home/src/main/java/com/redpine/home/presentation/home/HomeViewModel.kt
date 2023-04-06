package com.redpine.home.presentation.home

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import com.redpine.core.model.card.*
import com.redpine.home.R
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
            HorizontalGrid(R.string.New, listNewDog,1),
            HorizontalGrid(R.string.Recent_seen, listRecentSeenDog),
            VerticalGrid(R.string.News, listNews,1),
        )
    }

}

interface HomeScreen {
    val titleId: Int
    val list: List<Item>
    val spanCount: Int
}

class HorizontalGrid(
    override val titleId: Int,
    override val list: List<Item>,
    override val spanCount:Int = 2
):HomeScreen

class VerticalGrid(
    override val titleId: Int,
    override val list: List<Item>,
    override val spanCount:Int = 2
):HomeScreen
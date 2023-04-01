package com.redpine.home.presentation.home

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import com.redpine.core.model.card.*
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    private val _newDogList = mutableListOf<NewDog>()
    private val _recentSeenDogList = mutableListOf<RecentSeenDog>()
    private val _newsList = mutableListOf<News>()
    init {
        for(i in 1..10){
            _newDogList.add(NewDog(i-1,"number $i", "age ${i+5} years", Random.nextBoolean(),Random.nextBoolean()))
            _recentSeenDogList.add(RecentSeenDog(i-1,"number $i", "age ${i+5} years", Random.nextBoolean(),Random.nextBoolean()))
            _newsList.add(News(i, "title $i", "some string $i"))
        }
    }
    val newDogList = _newDogList.toList()
    val recentSeenDogList = _recentSeenDogList.toList()
    val newsList = _newsList.toList()



}
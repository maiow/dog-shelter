package com.redpine.home.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import com.redpine.core.model.Dog
import com.redpine.core.model.Item
import com.redpine.core.model.ItemContainer
import com.redpine.core.model.News
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val api: Api,
) : ViewModel() {

    private val _dogList = mutableListOf<Dog>()
    private val _newsList = mutableListOf<News>()
    init {
        for(i in 1..10){
            _dogList.add(Dog(i-1,"number $i", "age ${i+5} years", Random.nextBoolean(),Random.nextBoolean()))
            _newsList.add(News(i, "title $i", "some string $i"))
        }
    }
    private val dogList = _dogList.toList()
    val newsList = _newsList.toList()
    private val _recentList = mutableListOf<Item>()
    private val _favoriteList = mutableListOf<Item>()

    init {
        dogList.forEach {
                if (it.isRecentSeen) _recentList.add(it)
                else _favoriteList.add(it)
        }
    }
    val recentList = _recentList.toList()
    val favoriteList = _favoriteList.toList()



}
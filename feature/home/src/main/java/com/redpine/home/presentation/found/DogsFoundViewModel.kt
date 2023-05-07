package com.redpine.home.presentation.found

import android.util.Log
import com.redpine.api.Api
import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class DogsFoundViewModel(api: Api) : BaseViewModel() {


    private val _foundDogList = mutableListOf<Dog>()
    private val _dogs = MutableStateFlow(emptyList<Dog>())
    val dogs = _dogs.asStateFlow()

    init {
        for (i in 1..10) {
            _foundDogList.add(
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
    }

    fun onItemClick(clickableView: ClickableView) {
        when (clickableView) {
            ClickableView.FAVORITE -> addToFavorites(clickableView.itemPosition)
            else -> {}
        }
    }

    fun getDogsByFilters(filters: String) = scopeLaunch {
        _dogs.value = _foundDogList.toList()
    }

    private fun addToFavorites(position: Int) {

        val newList = _dogs.value.toMutableList()
        newList[position] = newList[position].copy(isFavorite = !newList[position].isFavorite)
        _dogs.value = newList
    }
}
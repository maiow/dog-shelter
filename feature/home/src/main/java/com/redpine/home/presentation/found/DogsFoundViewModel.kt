package com.redpine.home.presentation.found

import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.redpine.api.Api
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import kotlin.random.Random

class DogsFoundViewModel(api: Api) : ViewModel() {


    private val _foundDogList = mutableListOf<Dog>()

    init {
        for (i in 1..10) {
            _foundDogList.add(
                Dog(
                    i - 1, "number $i", "age ${i + 5} years", "", Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
        }
    }

    val foundDogList = _foundDogList.toList()

    fun onItemClick(clickableView: ClickableView, item: Item, fragment: DogsFoundFragment) {
        when (clickableView) {
            ClickableView.FAVORITE -> addToFavorites(item)
            ClickableView.DOG -> fragment.findNavController()
                .navigate(DogsFoundFragmentDirections.actionDogsFoundFragmentToPetsCardFragment(item.id))
            else -> {}
        }
    }

    private fun addToFavorites(item: Item) {
        item as Dog
        item.isFavorite = !item.isFavorite
    }
}



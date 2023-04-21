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



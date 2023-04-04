package com.redpine.home.presentation.found

import androidx.lifecycle.ViewModel
import com.redpine.api.Api
import com.redpine.core.model.card.RecentSeenDog
import kotlin.random.Random

class DogsFoundViewModel(api: Api) : ViewModel() {


    private val _foundDogList = mutableListOf<RecentSeenDog>()

    init {
        for (i in 1..10) {
            _foundDogList.add(
                RecentSeenDog(
                    i - 1, "number $i", "age ${i + 5} years", Random.nextBoolean(),
                    Random.nextBoolean()
                )
            )
        }
    }

    val foundDogList = _foundDogList.toList()
}



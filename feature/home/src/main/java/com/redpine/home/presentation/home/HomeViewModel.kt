package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.home.data.FilteredDogs
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeScreenUseCase: HomeScreenUseCase,
    private val likeUseCase: LikeUseCase,
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<Grid>>(emptyList())
    val data = _data.asStateFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    fun createHomeScreen() = scopeLaunch {
        _data.value = homeScreenUseCase.getHomeScreenItems()
        delay(100)
    }


    @SuppressLint("SuspiciousIndentation")
    fun addToFavorites(itemPosition: Int, firstListPosition: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newData = _data.value.toMutableList()
            val firstList = newData[firstListPosition].list.toMutableList() as MutableList<Dog>

            firstList[itemPosition] =
                firstList[itemPosition].copy(isFavorite = !firstList[itemPosition].isFavorite)


           val secondListPosition = if (firstListPosition == 0) 1 else firstListPosition

            val oldSecondList = newData[secondListPosition].list.toMutableList() as MutableList<Dog>

            val newSecondList = addLike(firstList[itemPosition].id, oldSecondList)

            newData[firstListPosition] = (newData[firstListPosition] as HorizontalGrid).copy(list = firstList)
            Log.e("Kart",newData[firstListPosition].list[itemPosition].isFavorite.toString())
            newData[secondListPosition] = (newData[secondListPosition] as HorizontalGrid).copy(list = newSecondList)

            _data.value = newData

            val isSuccessFavorite =
                likeUseCase.makeLikeDislike(id, firstList[itemPosition].isFavorite)
            if (isSuccessFavorite) _data.value = newData
            else _isNavigateAuth.value = true
        }
    }

    fun onAllDogsClick() = viewModelScope.launch(Dispatchers.IO) {
        FilteredDogs.filteredDogsList = homeScreenUseCase.getAllDogs()
    }

    fun resetNavigateFlow() {
        _isNavigateAuth.value = false
    }


    fun addLike(dogId: Int, secondList: MutableList<Dog>): List<Dog> {
        for (position in secondList.indices) {
            if (dogId == secondList[position].id) {
                secondList[position] =
                    secondList[position].copy(isFavorite = !secondList[position].isFavorite)
                break
            }
        }
        return secondList
    }

    fun addLikeToBothLists(
        dogPosition: Int,
        firstList: MutableList<Dog>,
        secondList: MutableList<Dog>,
    ) {
        firstList[dogPosition] =
            firstList[dogPosition].copy(isFavorite = !firstList[dogPosition].isFavorite)
        for (dog in secondList) {
            if (dog.id == firstList[dogPosition].id) {
                dog.isFavorite = firstList[dogPosition].isFavorite
                return
            }
        }
    }
}
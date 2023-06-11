package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.home.data.FilteredDogs
import com.redpine.home.domain.model.grid.Grid
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
    fun addToFavorites(itemPosition: Int, listPosition: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newData = _data.value.toMutableList()
            val firstList = newData[listPosition].list.toMutableList() as MutableList<Dog>
            val secondList =
                if (listPosition == 0) newData[1].list.toMutableList() as MutableList<Dog>
                else newData[0].list.toMutableList() as MutableList<Dog>
//            newList[itemPosition] =
//                newList[itemPosition].copy(isFavorite = !newList[itemPosition].isFavorite)
//            newData[listPosition] = (newData[listPosition] as HorizontalGrid).copy(list = newList)
            addLikeToBothLists(itemPosition, firstList, secondList)
//            TODO()
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
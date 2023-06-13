package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.model.Dog
import com.redpine.core.state.LoadState
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.domain.model.grid.HorizontalGrid
import com.redpine.home.domain.usecase.HomeScreenUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import com.redpine.home.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeScreenUseCase: HomeScreenUseCase,
    private val likeUseCase: LikeUseCase,
    private val searchUseCase: SearchUseCase,
    private val authDialogPrefs: AuthDialogPrefs,
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<Grid>>(emptyList())
    val data = _data.asStateFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    private val _foundDog = MutableStateFlow<Dog?>(null)
    val foundDog = _foundDog.asStateFlow()

    var authDialogIsShown = authDialogPrefs.isShown()

    fun createHomeScreen() = scopeLaunch {
        _data.value = homeScreenUseCase.getHomeScreenItems()
        delay(100)
    }

    @SuppressLint("SuspiciousIndentation")
    fun addToFavorites(itemPosition: Int, firstListPosition: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newData = _data.value.toMutableList()
            val firstList =
                newData[firstListPosition].list.toMutableList() as MutableList<Dog>
            firstList[itemPosition] =
                firstList[itemPosition].copy(isFavorite = !firstList[itemPosition].isFavorite)
            newData[firstListPosition] =
                (newData[firstListPosition] as HorizontalGrid).copy(list = firstList)

            if(_data.value.size == 3){
                val secondListPosition = if (firstListPosition == 0) 1 else 0
                val oldSecondList =
                    newData[secondListPosition].list.toMutableList() as MutableList<Dog>
                val newSecondList = addLike(firstList[itemPosition].id, oldSecondList)
                newData[secondListPosition] =
                    (newData[secondListPosition] as HorizontalGrid).copy(list = newSecondList)
            }

            val isSuccessFavorite =
                likeUseCase.makeLikeDislike(id, firstList[itemPosition].isFavorite)
            if (isSuccessFavorite) _data.value = newData
            else {
                _isNavigateAuth.value = true
                authDialogIsShown = authDialogPrefs.isShown()
            }
        }
    }

    fun onAllDogsClick() = viewModelScope.launch(Dispatchers.IO) {
        homeScreenUseCase.getAllDogs()
    }

    fun resetNavigateFlow() {
        _isNavigateAuth.value = false
    }

    fun onDogSearchClick(query: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.START
            _foundDog.value = searchUseCase.searchDogByName(query)
            /**передаем и потом обнуляем собаку, иначе по бэкстеку не вернуться*/
            delay(1)
            _foundDog.value = null
        }
    }

    fun rememberAuthDialogIsShown() {
        authDialogPrefs.rememberAuthDialogIsShown()
    }

    private fun addLike(dogId: Int, secondList: MutableList<Dog>): List<Dog> {
        for (position in secondList.indices) {
            if (dogId == secondList[position].id) {
                secondList[position] =
                    secondList[position].copy(isFavorite = !secondList[position].isFavorite)
                break
            }
        }
        return secondList
    }
}
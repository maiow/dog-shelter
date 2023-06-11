package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
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
    private val searchUseCase: SearchUseCase
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<Grid>>(emptyList())
    val data = _data.asStateFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    private val _foundDog = MutableStateFlow<Dog?>(null)
    val foundDog = _foundDog.asStateFlow()
//    private val _foundDog = MutableSharedFlow<Dog?>()
//    val foundDog = _foundDog.asSharedFlow()

    fun createHomeScreen() = scopeLaunch {
        _data.value = homeScreenUseCase.getHomeScreenItems()
        delay(500)
    }

    @SuppressLint("SuspiciousIndentation")
    fun addToFavorites(itemPosition: Int, listPosition: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newData = _data.value.toMutableList()
            val newList = newData[listPosition].list.toMutableList() as MutableList<Dog>
            newList[itemPosition] =
                newList[itemPosition].copy(isFavorite = !newList[itemPosition].isFavorite)
            newData[listPosition] = (newData[listPosition] as HorizontalGrid).copy(list = newList)
            val isSuccessFavorite =
                likeUseCase.makeLikeDislike(id, newList[itemPosition].isFavorite)
            if (isSuccessFavorite) _data.value = newData
            else _isNavigateAuth.value = true
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
}
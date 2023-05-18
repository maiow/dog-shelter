package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redpine.core.domain.model.Dog
import com.redpine.core.base.BaseViewModel
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
    private val likeUseCase: LikeUseCase
) : BaseViewModel() {



    private val _data = MutableStateFlow<List<Grid>>(emptyList())
    val data = _data.asStateFlow()

    init {
        createHomeScreen()
    }

    private fun createHomeScreen() = scopeLaunch {
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
            if (likeUseCase.makeLikeDislike(id, newList[itemPosition].isFavorite))
            _data.value = newData
        }
    }
}
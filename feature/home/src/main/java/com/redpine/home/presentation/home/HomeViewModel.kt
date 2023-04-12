package com.redpine.home.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.redpine.api.Api
import com.redpine.core.model.card.*
import com.redpine.core.tools.ClickableView
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import com.redpine.home.domain.model.homeScreen.VerticalGrid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _data = MutableStateFlow<List<HomeScreen>>(emptyList())
    val data = _data.asStateFlow()

    init {
        createHomeScreen()
    }

    private fun createHomeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            _data.value = repository.getItems()
        }
    }

    fun onItemClick(clickableView: ClickableView, item: Item) {
        when (clickableView) {
            ClickableView.FAVORITE -> {
                addToFavorites(
                    (item as Dog),
                    ClickableView.FAVORITE.itemPosition,
                    ClickableView.FAVORITE.listPosition
                )
                Log.d(TAG, "item: ${ClickableView.FAVORITE.itemPosition}")
                Log.d(TAG, "list: ${ClickableView.FAVORITE.listPosition}")
            }
        }
    }

    private fun addToFavorites(item: Dog, itemPosition: Int, listPosition: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newData = _data.value.toMutableList()
            val newList = newData[listPosition].list.toMutableList() as MutableList<Dog>
            newList[itemPosition] =
                newList[itemPosition].copy(isFavorite = !newList[itemPosition].isFavorite)
            newData[listPosition] = (newData[listPosition] as HorizontalGrid).copy(list = newList)
            _data.value = newData
        }
    }
}








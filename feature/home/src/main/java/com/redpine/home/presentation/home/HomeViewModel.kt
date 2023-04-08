package com.redpine.home.presentation.home

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
            ClickableView.FAVORITE -> addToFavorites(item)
        }
    }

    private fun addToFavorites(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            item as Dog
            item.isFavorite = !item.isFavorite
        }
    }
}








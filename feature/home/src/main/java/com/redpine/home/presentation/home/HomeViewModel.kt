package com.redpine.home.presentation.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.core.tools.DOG_CONTAINER
import com.redpine.core.tools.NEWS_CONTAINER
import com.redpine.home.R
import com.redpine.home.domain.Repository
import com.redpine.home.domain.model.homeScreen.HomeScreen
import com.redpine.home.domain.model.homeScreen.HorizontalGrid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    fun onItemClick(clickableView: ClickableView, item: Item, fragment: HomeFragment) {
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
            ClickableView.DOG -> fragment.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToPetsCardFragment(item.id))
            else -> {}
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

    fun onAllButtonClick(clickableView: ClickableView, fragment: HomeFragment) {
        Log.d(TAG, "onAllButtonClick: ${clickableView.container}")
        when(clickableView.container){
            DOG_CONTAINER -> fragment.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDogsFoundFragment(""))
            NEWS_CONTAINER -> fragment.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsListFragment())
        }
    }
}








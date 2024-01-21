package com.redpine.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.favorites.domain.usecase.DislikeUseCase
import com.redpine.favorites.domain.usecase.FavoritesUseCase
import com.redpine.favorites.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase,
    private val dislikeUseCase: DislikeUseCase,
    private val searchUseCase: SearchUseCase,
) : BaseViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs = _dogs.asStateFlow()

    private val _foundDog = MutableSharedFlow<Dog>()
    val foundDog = _foundDog.asSharedFlow()

    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth.asStateFlow()

    fun getDogs() = scopeLaunch {
        _dogs.value = favoritesUseCase.getFavoriteDogs()
    }

    fun onLikeClick(clickableView: ClickableView, id: Int) =
        deleteFromFavorites(clickableView.itemPosition, id)

    private fun deleteFromFavorites(position: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newList = _dogs.value.toMutableList()
            newList.removeAt(position)
            if (dislikeUseCase.makeDislike(id))
                _dogs.value = newList
        }
    }

    fun checkAuth() = scopeLaunch {
        _isAuth.value = favoritesUseCase.isUserAuthorized()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun onDogSearchClick(query: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.START
            _foundDog.emit(searchUseCase.searchDogByName(query))
            /**передаем и потом обнуляем собаку, иначе по бэкстеку не вернуться*/
            delay(10)
            _foundDog.resetReplayCache()
        }
    }
}
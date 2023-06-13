package com.redpine.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.model.Dog
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.DislikeUseCase
import com.redpine.favorites.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dislikeUseCase: DislikeUseCase,
    private val searchUseCase: SearchUseCase,
    private val authDialogPrefs: AuthDialogPrefs,
) : BaseViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs = _dogs.asStateFlow()

    private val _foundDog = MutableStateFlow<Dog?>(null)
    val foundDog = _foundDog.asStateFlow()

    private val _isAuth = MutableStateFlow<Boolean>(false)
    val isAuth = _isAuth.asStateFlow()

    private val _authDialogIsShown = MutableStateFlow(authDialogPrefs.isShown())
    val authDialogIsShown = _authDialogIsShown.asStateFlow()


    fun getDogInfo() = scopeLaunch {
        _dogs.value = favoritesRepository.getFavoriteDogs()
    }

    fun onRetryButtonClick() = getDogInfo()

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

    fun onDogSearchClick(query: String) {
        viewModelScope.launch(Dispatchers.IO + handler) {
            _loadState.value = LoadState.START
            _foundDog.value = searchUseCase.searchDogByName(query)
            /**передаем и потом обнуляем собаку, иначе по бэкстеку не вернуться*/
            delay(1)
            _foundDog.value = null
        }
    }

    fun checkAuth(){
        viewModelScope.launch(Dispatchers.IO){
            _isAuth.value = favoritesRepository.isUserAuthorized()
            _authDialogIsShown.value = authDialogPrefs.isShown()
        }
    }

    fun resetAuthCheck(){
        _isAuth.value = false
    }

    fun rememberAuthDialogIsShown() {
        authDialogPrefs.rememberAuthDialogIsShown()
    }
}
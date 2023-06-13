package com.redpine.home.presentation.found

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.AuthDialogPrefs
import com.redpine.core.domain.model.Dog
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.home.data.FilteredDogs
import com.redpine.home.domain.usecase.LikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogsFoundViewModel @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val authDialogPrefs: AuthDialogPrefs,
) : BaseViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs = _dogs.asStateFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    private val _authDialogIsShown = MutableStateFlow(authDialogPrefs.isShown())
    val authDialogIsShown = _authDialogIsShown.asStateFlow()

    init {
        while (FilteredDogs.filteredDogsList == null)
            _loadState.value = LoadState.LOADING
        _dogs.value = FilteredDogs.filteredDogsList ?: emptyList()
        _loadState.value = LoadState.SUCCESS
    }

    fun onLikeClick(clickableView: ClickableView, id: Int) =
        addToFavorites(clickableView.itemPosition, id)

    fun onFilterButtonClick() {
        FilteredDogs.filteredDogsList = null
    }

    fun onDestroyView() {
        FilteredDogs.filteredDogsList = null
    }

    private fun addToFavorites(position: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newList = _dogs.value.toMutableList()
            newList[position] = newList[position].copy(isFavorite = !newList[position].isFavorite)
            if (likeUseCase.makeLikeDislike(id, newList[position].isFavorite))
                _dogs.value = newList
            else {
                _isNavigateAuth.value = true
                _authDialogIsShown.value = authDialogPrefs.isShown()
            }
        }
    }

    fun resetNavigateFlow() {
        _isNavigateAuth.value = false
    }

    fun rememberAuthDialogIsShown() {
        authDialogPrefs.rememberAuthDialogIsShown()
    }
}
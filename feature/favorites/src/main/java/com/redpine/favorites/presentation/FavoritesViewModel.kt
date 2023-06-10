package com.redpine.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.core.tools.ClickableView
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.usecase.DislikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dislikeUseCase: DislikeUseCase
) : BaseViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs = _dogs.asStateFlow()

    private fun getDogInfo() = scopeLaunch {
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

    fun onStart() = getDogInfo()
}
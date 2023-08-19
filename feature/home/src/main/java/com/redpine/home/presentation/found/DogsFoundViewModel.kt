package com.redpine.home.presentation.found

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.Dog
import com.redpine.core.tools.ClickableView
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.usecase.FilteredDogsUseCase
import com.redpine.home.domain.usecase.LikeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DogsFoundViewModel @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val filteredDogsUseCase: FilteredDogsUseCase,
    private val filterUseCase: FilterUseCase
) : BaseViewModel() {

    private val _dogs = MutableStateFlow<List<Dog>>(emptyList())
    val dogs = _dogs.asStateFlow()

    private val _isNavigateAuth = MutableStateFlow(false)
    val isNavigateAuth = _isNavigateAuth.asStateFlow()

    private var isFromHome = false

    fun getDogs(fromHome: Boolean) = scopeLaunch {
        isFromHome = fromHome
        if (isFromHome) clearFilters()
        _dogs.value = filteredDogsUseCase.getFilteredDogs()
    }

    fun onLikeClick(clickableView: ClickableView, id: Int) =
        addToFavorites(clickableView.itemPosition, id)

    fun clearFilters() = filterUseCase.setFilters(null)

    fun getFilters() = filterUseCase.getFilters()

    private fun addToFavorites(position: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newList = _dogs.value.toMutableList()
            newList[position] =
                newList[position].copy(isFavorite = !newList[position].isFavorite)
            if (likeUseCase.makeLikeDislike(id, newList[position].isFavorite))
                _dogs.value = newList
            else _isNavigateAuth.value = true
        }
    }

    fun onRetryButtonClick() = getDogs(isFromHome)

    fun resetNavigateFlow() {
        _isNavigateAuth.value = false
    }

}
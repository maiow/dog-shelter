package com.redpine.home.presentation.news

import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.News
import com.redpine.core.state.LoadState
import com.redpine.home.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SingleNewsViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _data = MutableSharedFlow<News>()
    val data = _data.asSharedFlow()

    fun getSingleNews(id: Int) {
        viewModelScope.launch(Dispatchers.IO + handler){
            _loadState.value = LoadState.LOADING
            delay(2000)
            _data.emit(repository.getSingleNews(id))
            _loadState.value = LoadState.SUCCESS
        }
    }
}
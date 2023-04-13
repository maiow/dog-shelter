package com.redpine.home.presentation.news

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<News>>(emptyList())
    val data = _data.asStateFlow()

    init {
        getNewsList()
    }

    private fun getNewsList() {
        viewModelScope.launch(Dispatchers.IO){
            _data.value = repository.getNewsList()
            Log.d(TAG, "getNewsList: ${_data.value}")
        }
    }
}
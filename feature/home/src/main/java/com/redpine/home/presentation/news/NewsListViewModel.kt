package com.redpine.home.presentation.news

import com.redpine.core.base.BaseViewModel
import com.redpine.core.domain.model.News
import com.redpine.home.domain.usecase.ListNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val listNewsUseCase: ListNewsUseCase
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<News>>(emptyList())
    val data = _data.asStateFlow()

    init {
        getNewsList()
    }

    private fun getNewsList() = scopeLaunch {
        _data.value = listNewsUseCase.getNewsList()
    }
}
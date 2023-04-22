package com.redpine.home.presentation.news

import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.SingleNewsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class SingleNewsViewModel @Inject constructor(
    private val singleNewsUseCase: SingleNewsUseCase
) : BaseViewModel() {

    private val _data = MutableSharedFlow<News>()
    val data = _data.asSharedFlow()

    fun getSingleNews(id: Int) = scopeLaunch {
        _data.emit(singleNewsUseCase.getNewsById(id))
    }
}
package com.redpine.home.presentation.news

import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.News
import com.redpine.home.domain.repository.NewsRepository
import com.redpine.home.domain.usecase.ListNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**в Firebase 3 новости, поэтому показываем все, что есть, при переходе на свой бэк нужно будет
 * в запрос добавлять:
const val NEWS_COUNT = 3
 */

class NewsListViewModel @Inject constructor(
    private val listNewsUseCase: ListNewsUseCase
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<News>>(emptyList())
    val data = _data.asStateFlow()

    init {
        getNewsList()
    }

    //TODO: нужна проверка на непустой список и если пришел пустой, добавить обработку
// newsResponse.exception, показывать юзеру что-то про состояние сети
    private fun getNewsList() = scopeLaunch {
        _data.value = listNewsUseCase.getNewsList()
    }
}
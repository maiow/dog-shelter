package com.redpine.home.presentation.news

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.redpine.core.base.BaseViewModel
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.core.tools.ClickableView
import com.redpine.home.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**в Firebase 3 новости, поэтому показываем все, что есть, при переходе на свой бэк нужно будет
 * в запрос добавлять:
const val NEWS_COUNT = 3
 */

class NewsListViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _data = MutableStateFlow<List<News>>(emptyList())
    val data = _data.asStateFlow()

    init {
        getNewsList()
    }

    private fun getNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
//TODO: нужна проверка на непустой список и если пришел пустой, добавить обработку
// newsResponse.exception, показывать юзеру что-то про состояние сети

            _data.value = repository.getNewsList().newsList!!
            Log.d(TAG, "getNewsList: ${_data.value}")
        }
    }

    fun onItemClick(clickableView: ClickableView, item: Item, fragment: NewsListFragment) {
        when (clickableView) {
            ClickableView.NEWS -> fragment.findNavController().navigate(
                NewsListFragmentDirections.actionNewsListFragmentToSingleNewsFragment(item.id)
            )

            else -> {}
        }
    }
}
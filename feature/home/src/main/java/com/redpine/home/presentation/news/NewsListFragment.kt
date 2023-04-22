package com.redpine.home.presentation.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentNewsListBinding
import com.redpine.home.presentation.home.delegate.ItemAdapter
import kotlinx.coroutines.launch

class NewsListFragment : HomeBaseFragment<FragmentNewsListBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentNewsListBinding.inflate(inflater)
    private val viewModel: NewsListViewModel by lazy { initViewModel() }
    private val adapter by lazy { ItemAdapter(::onItemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowObserver(viewModel.data) { news -> observeData(news) }
        setSearch()
        setAdapter()
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.NEWS) navigate(item.id)
    }

    private fun navigate(itemId: Int) = findNavController()
        .navigate(NewsListFragmentDirections.actionNewsListFragmentToSingleNewsFragment(itemId))

    private fun observeData(news: List<News>) {
        adapter.items = news
    }

    private fun setAdapter() {
        binding.recycler.adapter = adapter
    }

    private fun setSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
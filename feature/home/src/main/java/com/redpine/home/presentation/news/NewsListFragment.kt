package com.redpine.home.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.redpine.core.domain.model.Item
import com.redpine.core.domain.model.News
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentNewsListBinding
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

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
        if (clickableView == ClickableView.NEWS) navigateToSingleNews(item.id)
    }

    private fun navigateToSingleNews(itemId: Int) = findNavController()
        .navigate(NewsListFragmentDirections.actionNewsListFragmentToSingleNewsFragment(itemId))

    private fun observeData(news: List<News>) {
        adapter.submitList(news)
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
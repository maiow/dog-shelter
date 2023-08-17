package com.redpine.home.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.redpine.core.domain.model.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentNewsListBinding
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

class NewsListFragment : HomeBaseFragment<FragmentNewsListBinding>() {

    private val viewModel: NewsListViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentNewsListBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter by lazy { ItemAdapter(::onItemClick) }
        binding.recycler.adapter = adapter
        flowObserver(viewModel.data) { news -> adapter.submitList(news) }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.NEWS) navigateToSingleNews(item.id)
    }

    private fun navigateToSingleNews(itemId: Int) = findNavController()
        .navigate(NewsListFragmentDirections.actionNewsListFragmentToSingleNewsFragment(itemId))
}
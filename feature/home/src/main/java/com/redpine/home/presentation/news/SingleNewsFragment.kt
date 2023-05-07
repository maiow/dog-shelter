package com.redpine.home.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.redpine.core.model.card.News
import com.redpine.core.state.LoadState
import com.redpine.core.tools.loadImage
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentSingleNewsBinding

class SingleNewsFragment : HomeBaseFragment<FragmentSingleNewsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentSingleNewsBinding.inflate(inflater)
    private val viewModel: SingleNewsViewModel by lazy { initViewModel() }
    private val args by navArgs<SingleNewsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleNews(args.newsId)
        flowObserver(viewModel.data) { news -> bindNews(news) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }

    }

    private fun bindNews(news: News) {
        binding.newsPreview.loadImage(news.imageUrl)
        binding.newsTitle.text = news.title
        binding.newsBody.text = news.body
    }

    private fun loadingObserve(loadState: LoadState) {
        binding.progressBar.isVisible = loadState == LoadState.LOADING
        val isSuccess = loadState == LoadState.SUCCESS
        binding.newsBody.isVisible = isSuccess
        binding.newsTitle.isVisible = isSuccess
        if (LoadState.ERROR_NETWORK == loadState) Toast.makeText(
            requireContext(), "loading error", Toast.LENGTH_SHORT
        ).show()
    }
}
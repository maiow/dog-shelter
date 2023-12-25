package com.redpine.home.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.redpine.core.base.BaseFragmentWithViewModel
import com.redpine.core.component.getComponent
import com.redpine.core.domain.model.News
import com.redpine.core.extensions.loadImage
import com.redpine.core.state.LoadState
import com.redpine.home.databinding.FragmentSingleNewsBinding
import com.redpine.home.di.component.HomeComponent

class SingleNewsFragment : BaseFragmentWithViewModel<FragmentSingleNewsBinding,SingleNewsViewModel>() {

    private val args by navArgs<SingleNewsFragmentArgs>()
    override fun initBinding(inflater: LayoutInflater) = FragmentSingleNewsBinding.inflate(inflater)
    override fun inject() {
        requireContext().getComponent<HomeComponent>().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleNews(args.newsId)
        flowObserver(viewModel.data) { news -> bindNews(news) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }

    }

    private fun bindNews(news: News) {
        binding.newsPreview.loadImage(news.imageUrl)
        binding.newsTitle.text = news.title
        val body = news.body
            .replace("\\n", "\n")
            .replace("    ", "\n\n")
            .replace("•", "  •")
        binding.newsBody.text = body
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
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
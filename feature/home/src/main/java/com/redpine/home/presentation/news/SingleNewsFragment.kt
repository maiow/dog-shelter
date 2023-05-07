package com.redpine.home.presentation.news

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.redpine.core.state.LoadState
import com.redpine.core.tools.loadImage
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentSingleNewsBinding
import kotlinx.coroutines.launch

class SingleNewsFragment : HomeBaseFragment<FragmentSingleNewsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentSingleNewsBinding.inflate(inflater)
    private val viewModel: SingleNewsViewModel by lazy { initViewModel() }
    private val args by navArgs<SingleNewsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData(args.newsId)
        infoLoadingObserve()
    }

    @SuppressLint("SetTextI18n")
    private fun observeData(newsId: Int) {
        viewModel.getSingleNews(newsId)
        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(TAG, "observeNews: ")
            viewModel.data.collect { newsResponse ->
                newsResponse.news.let { news ->
                    if (news != null) {
                        Log.d(TAG, "observeData: ${news.body}")
                        binding.newsPreview.loadImage(news.imageUrl)
                        binding.newsTitle.text = news.title
                        val body = news.body
                            .replace("\\n", "\n")
                            .replace("    ", "\n\n")
                            .replace("•", "  •")
                        binding.newsBody.text = body
                    }
                    //TODO: нужно добавить обработку newsResponse.exception
                }
            }
        }
    }

    private fun infoLoadingObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            Log.d(TAG, "infoLoadingObserve: ")
            viewModel.loadState.collect { loadState ->
                when (loadState) {
                    LoadState.LOADING -> {
                        binding.progressBar.isVisible = true
                        binding.newsBody.isVisible = false
                        binding.newsTitle.isVisible = false
                    }

                    LoadState.ERROR_NETWORK -> {
                        binding.progressBar.isVisible = false
                        binding.newsBody.isVisible = false
                        binding.newsTitle.isVisible = false
                        Toast.makeText(requireContext(), "loading error", Toast.LENGTH_SHORT)
                            .show()
                    }

                    LoadState.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        binding.newsBody.isVisible = true
                        binding.newsTitle.isVisible = true
                    }

                    else -> {}

                }
            }
        }
    }
}
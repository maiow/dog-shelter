package com.redpine.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.favorites.FavoritesBaseFragment
import com.redpine.favorites.databinding.FragmentFavoritesBinding
import com.redpine.favorites.presentation.adapter.FavoritesAdapter

class FavoritesFragment : FavoritesBaseFragment<FragmentFavoritesBinding>() {

    private val viewModel: FavoritesViewModel by lazy { initViewModel() }
    private val adapter by lazy { FavoritesAdapter(::onItemClick) }
    override fun initBinding(inflater: LayoutInflater) = FragmentFavoritesBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
    }

    private fun loadContent(dogs: List<Dog>) {
        adapter.submitList(dogs)
        binding.title.isVisible = dogs.isNotEmpty()
//            binding.title.text = resources.getQuantityString(
//                R.plurals.found_pets,
//                data.size,
//                data.size
//            )
        binding.noneFound.isVisible = dogs.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            recyclerView.isVisible = (loadState == LoadState.SUCCESS)
            if (LoadState.ERROR_NETWORK == loadState) {
                connectionError.error.isVisible = true
                connectionError.retryButton.setOnClickListener {
                    viewModel.onRetryButtonClick()
                }
            }
        }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
//        if (clickableView == ClickableView.DOG) navigateToPetsCardFragment(item as Dog)
//        else
        viewModel.onLikeClick(clickableView, item.id)
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }
}
package com.redpine.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.extensions.setSubmitTextListener
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.favorites.FavoritesBaseFragment
import com.redpine.favorites.R
import com.redpine.favorites.databinding.FragmentFavoritesBinding
import com.redpine.favorites.presentation.adapter.FavoritesAdapter

class FavoritesFragment : FavoritesBaseFragment<FragmentFavoritesBinding>() {

    private val viewModel: FavoritesViewModel by lazy { initViewModel() }
    private val adapter by lazy { FavoritesAdapter(::onItemClick) }
    override fun initBinding(inflater: LayoutInflater) = FragmentFavoritesBinding.inflate(inflater)

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInterface()
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.foundDog) { dog -> observeSearchResult(dog) }
    }

    private fun loadContent(dogs: List<Dog>) {
        adapter.submitList(dogs)
        binding.title.isVisible = dogs.isNotEmpty()
        binding.noneFound.isVisible = dogs.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.onRetryButtonClick()
            }
            noDogs.isVisible = loadState == LoadState.NULL_SEARCH
        }
    }

    private fun setUserInterface() {
        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.actionFavoritesToFilter)
        }

        binding.searchView.setSubmitTextListener { query ->
            viewModel.onDogSearchClick(query)
        }
        /**клик на лупу*/
        binding.searchView.setOnClickListener {
            binding.noDogs.isVisible = false
        }
        /**клик на крестик*/
        val searchCloseButton: View =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        searchCloseButton.setOnClickListener {
            binding.searchView.setQuery("", false)
            binding.noDogs.isVisible = false
        }
    }

    private fun observeSearchResult(dog: Dog?) {
        if (dog == null) binding.noDogs.isVisible = true
        else {
            findNavController().navigate(R.id.actionFavoritesToPetsCard,
                Bundle().apply { putParcelable("dog", (dog)) })
        }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.DOG) {
            findNavController().navigate(
                R.id.actionFavoritesToPetsCard,
                Bundle().apply { putParcelable("dog", (item as Dog)) })
        } else
            viewModel.onLikeClick(clickableView, item.id)
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }
}
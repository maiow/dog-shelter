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

    override fun initBinding(inflater: LayoutInflater) = FragmentFavoritesBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter by lazy { FavoritesAdapter(::onItemClick) }
        viewModel.checkAuth()
        setUserInterface()
        viewModel.getDogs()
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs, adapter) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.isAuth) { isAuth -> authObserve(isAuth) }
    }

    private fun authObserve(auth: Boolean) {
        if (!auth) showDialog(com.redpine.core.R.string.auth_dialog_message) {
            navigate(R.id.actionFavoritesToAuth)
        }
    }

    private fun loadContent(dogs: List<Dog>, adapter: FavoritesAdapter) {
        adapter.submitList(dogs)
        binding.title.isVisible = dogs.isNotEmpty()
        binding.noneFound.isVisible = dogs.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.getDogs()
            }
            noDogs.isVisible = loadState == LoadState.NULL_SEARCH
        }
    }

    private fun setUserInterface() {
        binding.searchView.setSubmitTextListener { query ->
            viewModel.onDogSearchClick(query)
            flowObserver(viewModel.foundDog) { dog -> observeSearchResult(dog) }
        }

        binding.searchView.setOnClickListener {
            binding.noDogs.isVisible = false
        }

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
            findNavController().navigate(
                R.id.petsCardFragment,
                Bundle().apply { putParcelable("dog", (dog)) })
        }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.DOG) {
            findNavController().navigate(R.id.petsCardFragment,
                Bundle().apply { putParcelable("dog", (item as Dog)) })
        } else viewModel.onLikeClick(clickableView, item.id)
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }
}
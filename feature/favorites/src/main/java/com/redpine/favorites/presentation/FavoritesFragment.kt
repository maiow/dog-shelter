package com.redpine.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkAuth()
        setUserInterface()
        viewModel.getDogInfo()
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.isAuth){ isAuth -> authObserve(isAuth)}
    }

    private fun observeAuthDialogIsShown(isShown: Boolean) {
        if (!isShown) {
            showAuthDialog(R.id.authFragment) { viewModel.resetAuthCheck() }
            viewModel.rememberAuthDialogIsShown()
        } else {
            viewModel.resetAuthCheck()
        }
    }

    private fun authObserve(auth: Boolean) {
        if(!auth)
            flowObserver(viewModel.authDialogIsShown) { isShown -> observeAuthDialogIsShown(isShown) }
    }

    private fun loadContent(dogs: List<Dog>) {
        adapter.submitList(dogs)
        binding.title.isVisible = dogs.isNotEmpty()
        binding.noneFound.isVisible = dogs.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            recyclerView.isVisible = loadState == LoadState.SUCCESS
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.onRetryButtonClick()
            }
        }
    }

    private fun setUserInterface() {
        setSearch()
        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.actionFavoritesToFilter)
        }
    }

    //TODO: скопировать реализацию из Home, когда там будет готов поиск
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
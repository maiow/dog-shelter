package com.redpine.home.presentation.home

import android.content.Intent
import android.net.Uri
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
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.presentation.home.adapter.adapter.GridAdapter

class HomeFragment : HomeBaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by lazy { initViewModel() }
    private val adapter by lazy { GridAdapter(::onItemClick) }
    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.createHomeScreen()
        setUserInterface()
        binding.recycler.adapter = adapter
        flowObserver(viewModel.data) { data -> observeData(data) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.isNavigateAuth) { action -> observeNavigateAuth(action) }
        flowObserver(viewModel.foundDog) { dog -> observeSearchResult(dog) }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item?) {
        val isNullItem = item == null
        when (clickableView) {
            ClickableView.DOG -> if (!isNullItem)
                navigate(HomeFragmentDirections.actionHomeFragmentToPetsCardFragment(item!! as Dog))

            ClickableView.NEWS -> if (!isNullItem)
                navigate(HomeFragmentDirections.actionHomeFragmentToSingleNewsFragment(item!!.id))

            ClickableView.DOG_ALL_BUTTON -> {
                viewModel.onAllDogsClick()
                navigate(HomeFragmentDirections.actionHomeFragmentToDogsFoundFragment(getString(R.string.all_dogs)))
            }

            ClickableView.NEWS_ALL_BUTTON ->
                navigate(HomeFragmentDirections.actionHomeFragmentToNewsListFragment())

            ClickableView.FAVORITE -> {
                viewModel.addToFavorites(
                    clickableView.itemPosition,
                    clickableView.listPosition,
                    item!!.id
                )
            }
        }
    }

    private fun observeAuthDialogIsShown(isShown: Boolean) {
        if (!isShown) {
            showAuthDialog(R.id.auth_nav_graph) { viewModel.resetNavigateFlow() }
            viewModel.rememberAuthDialogIsShown()
        } else {
            navigate(R.id.auth_nav_graph)
            viewModel.resetNavigateFlow()
        }
    }

    private fun observeNavigateAuth(isNavigation: Boolean) {
        if (isNavigation)
            observeAuthDialogIsShown(viewModel.authDialogIsShown)
    }

    private fun observeData(data: List<Grid>) {
        adapter.submitList(data)
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            noDogs.isVisible = (loadState == LoadState.NULL_SEARCH)
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.createHomeScreen()
            }
        }
    }

    private fun setUserInterface() {
        setSearching()
        binding.filterButton.setOnClickListener {
            navigate(HomeFragmentDirections.actionHomeFragmentToFilterFragment())
        }
        binding.helpButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_helpFragment)
        }
        binding.btnVK.setOnClickListener { onSocialClick(VK_URI) }
        binding.btnTG.setOnClickListener { onSocialClick(TG_URI) }
    }

    private fun onSocialClick(uri: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }

    private fun setSearching() {
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
        else findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToPetsCardFragment(dog))
    }

    override fun onDestroyView() {
        binding.recycler.adapter = null
        super.onDestroyView()
    }

    private companion object {
        const val VK_URI = "https://vk.com/redpine"
        const val TG_URI = "https://t.me/priutKS"
    }
}

package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }
    private val args by navArgs<DogsFoundFragmentArgs>()
    private val adapter by lazy { ItemAdapter(::onItemClick) }

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilterText(args.filters)
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs) }
        flowObserver(viewModel.isNavigateAuth) { action -> observeNavigateAuth(action) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        binding.filterButton.setOnClickListener {
            viewModel.onFilterButtonClick()
            findNavController().popBackStack()
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

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.DOG) navigateToPetsCardFragment(item as Dog)
        else viewModel.onLikeClick(clickableView, item.id)
    }

    private fun navigateToPetsCardFragment(dog: Dog) =
        findNavController().navigate(
            DogsFoundFragmentDirections.actionDogsFoundFragmentToPetsCardFragment(dog)
        )

    private fun setFilterText(filters: String) {
        binding.searchParamsView.text = filters
    }

    private fun loadContent(data: List<Item>) {
        adapter.submitList(data)
        binding.title.isVisible = data.isNotEmpty()
        binding.title.text = resources.getQuantityString(
            R.plurals.found_pets,
            data.size,
            data.size
        )
        binding.noneFound.isVisible = data.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                //TODO: handle retry button click
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}
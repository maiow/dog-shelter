package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.state.LoadState
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.MAX_POSSIBLE_AGE
import com.redpine.home.presentation.MIN_POSSIBLE_AGE
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter by lazy { ItemAdapter(::onItemClick) }
        setFilterText()
        viewModel.getDogs()
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs, adapter) }
        flowObserver(viewModel.isNavigateAuth) { action -> observeNavigateAuth(action) }
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        binding.filterButton.setOnClickListener {
            if (findNavController().previousBackStackEntry?.destination?.id != R.id.filterFragment)
                findNavController().navigate(R.id.action_dogsFoundFragment_to_filterFragment)
            else findNavController().popBackStack()
        }
    }

    private fun observeNavigateAuth(isNavigation: Boolean) {
        if (isNavigation) {
            showDialog(com.redpine.core.R.string.auth_dialog_message) { navigate(R.id.auth_nav_graph) }
            viewModel.resetNavigateFlow()
        }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.DOG) navigateToPetsCardFragment(item as Dog)
        else viewModel.onLikeClick(clickableView, item.id)
    }

    private fun navigateToPetsCardFragment(dog: Dog) = findNavController().navigate(
        DogsFoundFragmentDirections.actionDogsFoundFragmentToPetsCardFragment(dog)
    )

    private fun setFilterText() {
        val filters = viewModel.getFilters()
        binding.searchParamsView.text = if (filters != null) {
            buildString {
                append(
                    if (filters.isMale == null) getString(R.string.any_gender)
                    else if (!filters.isMale) getString(R.string.girl)
                    else getString(R.string.boy)
                )
                append(getString(R.string.size_for_found) + " ")
                append(
                    if (filters.size.isNotEmpty()) filters.size.joinToString(",")
                    else getString(R.string.any_for_found)
                )
                append(getString(R.string.age_for_found) + " ")
                if (filters.minAge == MIN_POSSIBLE_AGE &&
                    filters.maxAge == MAX_POSSIBLE_AGE
                ) {
                    append(getString(R.string.any_for_found))
                } else {
                    append(filters.minAge.toString())
                    append("-")
                    append(
                        resources.getQuantityString(
                            R.plurals.age_data, filters.maxAge, filters.maxAge.toString()
                        )
                    )
                }
            }
        } else getString(R.string.all_shelter_dogs)
    }

    private fun loadContent(data: List<Item>, adapter: ItemAdapter) {
        adapter.submitList(data)
        binding.title.isVisible = data.isNotEmpty()
        binding.title.text = resources.getQuantityString(
            R.plurals.found_pets, data.size, data.size
        )
        binding.noneFound.isVisible = data.isEmpty()
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.onRetryButtonClick()
            }
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        viewModel.clearFilters()
        super.onDestroy()
    }
}
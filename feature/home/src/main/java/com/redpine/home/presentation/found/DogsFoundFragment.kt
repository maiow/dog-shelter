package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.redpine.core.extensions.onClickToPopBackStack
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.home.adapter.delegate.ItemAdapter

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)
    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }
    private val args by navArgs<DogsFoundFragmentArgs>()

    private val adapter by lazy { ItemAdapter(::onItemClick) }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        if (clickableView == ClickableView.DOG) navigateToPetsCardFragment(item.id)
        viewModel.onItemClick(clickableView)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDogsByFilters(args.filters)
        setFilterText(args.filters)
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs){dogs-> loadContent(dogs)}
        binding.filterButton.onClickToPopBackStack()
    }

    private fun navigateToPetsCardFragment(id: Int) =
        findNavController().navigate(
            DogsFoundFragmentDirections.actionDogsFoundFragmentToPetsCardFragment(id)
        )

    private fun setFilterText(filters: String) {
        binding.searchParamsView.text = filters
    }

    private fun loadContent(data: List<Item>) {
        adapter.submitList(data)
    }
}
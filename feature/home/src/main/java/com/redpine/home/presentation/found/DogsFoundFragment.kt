package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.home.delegate.dogsDelegate

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)
    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }
    private val adapter by lazy {
        ListDelegationAdapter(
            dogsDelegate { clickableView, item ->
                onItemClick(clickableView, item)
            })
    }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        viewModel.onItemClick(clickableView, item)
    }

    private val args by navArgs<DogsFoundFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilterText(args.filters)
        loadContent(viewModel.foundDogList)

        binding.filterButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setFilterText(filters: String) {
        binding.searchParamsView.text = filters
    }

    private fun loadContent(data: List<Dog>) {
        adapter.items = data
        binding.recyclerView.adapter = adapter
    }
}
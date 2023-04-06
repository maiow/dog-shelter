package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.RecentSeenDog
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.home.delegate.dogListDelegate

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)
    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }
    private val adapter by lazy { ListDelegationAdapter(dogListDelegate()) }
    private val args by navArgs<DogsFoundFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilterText(args.filters)
        loadContent(viewModel.foundDogList)

        binding.filterButton.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setFilterText(filters: String) {
        binding.searchParamsView.text = filters
    }

    private fun loadContent(data: List<RecentSeenDog>) {
        adapter.items = data
        binding.recyclerView.adapter = adapter
    }
}
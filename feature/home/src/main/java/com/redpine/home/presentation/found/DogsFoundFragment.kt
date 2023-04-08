package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Dog
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.presentation.home.delegate.dogsDelegate
import com.redpine.home.presentation.tools.ClickableView
import com.redpine.home.presentation.tools.Query

class DogsFoundFragment : HomeBaseFragment<FragmentDogsFoundBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)
    private val viewModel: DogsFoundViewModel by lazy { initViewModel() }
    private val adapter by lazy {
        ListDelegationAdapter(dogsDelegate { query: Query, clickableView: ClickableView ->
            onClick(query, clickableView)
        })
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

    private fun onClick(query: Query, clickableView: ClickableView) {
        when (clickableView) {
            ClickableView.DOG -> findNavController()
                .navigate(DogsFoundFragmentDirections.actionDogsFoundFragmentToPetsCardFragment())
            else -> {}
        }
    }
}
package com.redpine.home.presentation.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.redpine.core.base.BaseFragmentWithViewModel
import com.redpine.core.component.getComponent
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.R
import com.redpine.home.databinding.FragmentDogsFoundBinding
import com.redpine.home.di.component.HomeComponent
import com.redpine.home.presentation.home.adapter.adapter.ItemAdapter

class DogsFoundFragment : BaseFragmentWithViewModel<FragmentDogsFoundBinding,DogsFoundViewModel>() {


    private val args by navArgs<DogsFoundFragmentArgs>()
    private val adapter by lazy { ItemAdapter(::onItemClick) }

    override fun initBinding(inflater: LayoutInflater) = FragmentDogsFoundBinding.inflate(inflater)
    override fun inject() {
        requireContext().getComponent<HomeComponent>().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilterText(args.filters)
        binding.recyclerView.adapter = adapter
        flowObserver(viewModel.dogs) { dogs -> loadContent(dogs) }
        binding.filterButton.setOnClickListener {
            viewModel.onFilterButtonClick()
            findNavController().popBackStack()
        }
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

    override fun onDestroyView() {
        binding.recyclerView.adapter = null
        viewModel.onDestroyView()
        super.onDestroyView()
    }
}
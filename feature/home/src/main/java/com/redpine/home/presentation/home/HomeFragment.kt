package com.redpine.home.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.domain.model.grid.Grid
import com.redpine.home.presentation.home.adapter.adapter.GridAdapter

class HomeFragment : HomeBaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel: HomeViewModel by lazy { initViewModel() }
    private val adapter by lazy { GridAdapter(::onItemClick) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInterface()
        binding.recycler.adapter = adapter
        flowObserver(viewModel.data) { data -> observeData(data) }
    }

    private fun onItemClick(clickableView: ClickableView, item: Item?) {
        val isNullItem = item == null
        when (clickableView) {
            ClickableView.DOG ->
                if (!isNullItem)
                    findNavController()
                        .navigate(HomeFragmentDirections.actionHomeFragmentToPetsCardFragment(item!!.id))

            ClickableView.NEWS ->
                if (!isNullItem)
                    findNavController()
                        .navigate(HomeFragmentDirections.actionHomeFragmentToSingleNewsFragment(item!!.id))

            ClickableView.DOG_ALL_BUTTON -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDogsFoundFragment(""))

            ClickableView.NEWS_ALL_BUTTON -> findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToNewsListFragment())

            ClickableView.FAVORITE ->
                viewModel.addToFavorites(clickableView.itemPosition, clickableView.listPosition)
        }
    }

    private fun observeData(data: List<Grid>) {
        adapter.submitList(data)
    }

    private fun setUserInterface() {
        setSearch()
        binding.filterButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_filterFragment)
        }
        binding.btnVK.setOnClickListener { onSocialClick(VK_URI) }
        binding.btnTG.setOnClickListener { onSocialClick(TG_URI) }
    }

    private fun onSocialClick(uri: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    }

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

    override fun onDestroyView() {
        binding.recycler.adapter = null
        super.onDestroyView()
    }

    private companion object {
        const val VK_URI = "https://vk.com/redpine"
        const val TG_URI = "https://t.me/priutKS"
    }
}

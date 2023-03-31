package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.ItemContainer
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.presentation.home.delegate.HomeAdapter
import com.redpine.home.presentation.home.delegate.containerListDelegate
import com.redpine.home.presentation.home.delegate.dogListDelegate

class HomeFragment : HomeBaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel: HomeViewModel by lazy { initViewModel() }
    private val adapter = ListDelegationAdapter(containerListDelegate())

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        adapter.apply {
            items = listOf(ItemContainer(viewModel.recentList), ItemContainer(viewModel.favoriteList), ItemContainer(viewModel.newsList))
            notifyDataSetChanged()
        }
    }

}
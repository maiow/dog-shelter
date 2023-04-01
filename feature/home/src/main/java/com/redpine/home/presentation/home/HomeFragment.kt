package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.container.NewDogContainer
import com.redpine.core.model.container.NewsContainer
import com.redpine.core.model.container.RecentSeenDogContainer
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.presentation.home.delegate.HomeAdapter
import com.redpine.home.presentation.home.delegate.newDogContainerListDelegate

class HomeFragment : HomeBaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)
    private val viewModel: HomeViewModel by lazy { initViewModel() }
    private val adapter = HomeAdapter()

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        adapter.apply {
            items = listOf(
                RecentSeenDogContainer(viewModel.recentSeenDogList),
                NewDogContainer(viewModel.newDogList),
                NewsContainer(viewModel.newsList)
            )
            notifyDataSetChanged()
        }
    }

}
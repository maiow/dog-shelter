package com.redpine.home.presentation.news

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.redpine.core.model.card.Item
import com.redpine.core.tools.ClickableView
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentNewsListBinding
import com.redpine.home.presentation.home.HomeViewModel
import com.redpine.home.presentation.home.delegate.newsDelegate
import com.redpine.home.presentation.tools.TG_URI
import com.redpine.home.presentation.tools.VK_URI
import kotlinx.coroutines.launch

class NewsListFragment : HomeBaseFragment<FragmentNewsListBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentNewsListBinding.inflate(inflater)
    private val viewModel: NewsListViewModel by lazy { initViewModel() }
    private val adapter by lazy { ListDelegationAdapter(newsDelegate(::onItemClick)) }

    private fun onItemClick(clickableView: ClickableView, item: Item) {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInterface()
        setAdapter()
        observeData()
    }

    private fun observeData(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect{ data ->
                adapter.items = data
                adapter.notifyDataSetChanged()
                Log.d(ContentValues.TAG, "fragment: $data")
            }
        }
    }

    private fun setAdapter(){
        binding.recycler.adapter = adapter
    }

    private fun setUserInterface() {
        setSearch()
//        binding.filterButton.setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_filterFragment)
//        }
//        binding.btnVK.setOnClickListener { onSocialClick(VK_URI) }
//        binding.btnTG.setOnClickListener { onSocialClick(TG_URI) }

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


}
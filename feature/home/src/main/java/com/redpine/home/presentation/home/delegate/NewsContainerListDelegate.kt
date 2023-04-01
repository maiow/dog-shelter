package com.redpine.home.presentation.home.delegate

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.model.container.NewDogContainer
import com.redpine.core.model.container.Container
import com.redpine.core.model.container.NewsContainer
import com.redpine.home.R
import com.redpine.home.databinding.ItemContainerViewHolderBinding

fun newsContainerListDelegate() =
    adapterDelegateViewBinding<NewsContainer, Container, ItemContainerViewHolderBinding>({ inflater, root ->
        ItemContainerViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            val newsAdapter = ListDelegationAdapter(newsListDelegate())
            binding.recyclerView.adapter = newsAdapter
            newsAdapter.items = item.list
            binding.itemTitle.text = context.getString(R.string.News)
            binding.recyclerView.layoutManager =
                GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)
        }
    }
package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.NewsViewHolderBinding
import com.redpine.core.model.Item
import com.redpine.core.model.News

fun newsListDelegate() =
    adapterDelegateViewBinding<News, Item, NewsViewHolderBinding>({ inflater, root ->
        NewsViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.newsTitle.text=item.title
            binding.newsBodyPreview.text=item.body
        }
    }
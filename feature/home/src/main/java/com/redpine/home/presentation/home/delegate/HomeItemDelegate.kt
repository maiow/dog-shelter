package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.databinding.NewsViewHolderBinding
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.home.presentation.tools.ClickableView
import com.redpine.home.presentation.tools.Query

fun newsDelegate() =
    adapterDelegateViewBinding<News, Item, NewsViewHolderBinding>({ inflater, root ->
        NewsViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.newsTitle.text=item.title
            binding.newsBodyPreview.text=item.body
        }
    }

fun dogsDelegate(onClick: (query: Query, clickableView: ClickableView) -> Unit) =
    adapterDelegateViewBinding<Dog, Item, DogViewHolderBinding>({ inflater, root ->
        DogViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.dogName.text = item.name
            binding.dogAge.text = item.age
            binding.dogHeight.text = item.testText

            binding.dogCard.setOnClickListener{
                onClick(Query(id = item.id), ClickableView.DOG)
            }
        }
    }
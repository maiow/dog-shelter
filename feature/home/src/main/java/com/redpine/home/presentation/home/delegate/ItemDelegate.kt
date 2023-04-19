package com.redpine.home.presentation.home.delegate

import android.content.ContentValues.TAG
import android.util.Log
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.databinding.NewsViewHolderBinding
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.core.tools.ClickableView
import com.redpine.core.tools.loadImage

fun newsDelegate(onItemClick: (ClickableView, Item) -> Unit) =
    adapterDelegateViewBinding<News, Item, NewsViewHolderBinding>({ inflater, root ->
        NewsViewHolderBinding.inflate(inflater, root, false)
    }) {
        binding.root.setOnClickListener {
            onItemClick(ClickableView.NEWS, item)
        }
        bind {
            binding.newsTitle.text = item.title
            binding.newsBodyPreview.text = item.body
            binding.newsPreview.loadImage(item.imageUrl)
        }
    }

fun dogsDelegate(onItemClick: (ClickableView, Item) -> Unit) =
    adapterDelegateViewBinding<Dog, Item, DogViewHolderBinding>({ inflater, root ->
        DogViewHolderBinding.inflate(inflater, root, false)
    }) {
        binding.btnFavorite.setOnClickListener {
            ClickableView.FAVORITE.itemPosition = bindingAdapterPosition
            onItemClick(ClickableView.FAVORITE, item)
            Log.d(TAG, "onClick: Dog[${item.name}] isLiked[${item.isFavorite}]")
        }
        binding.dogCard.setOnClickListener {
            onItemClick(ClickableView.DOG, item)
        }
        bind {
            binding.btnFavorite.isSelected = item.isFavorite
            Log.d(TAG, "payloadsItem: $it")
            Log.d(TAG, "onBind: Dog[${item.name}] isLiked[${item.isFavorite} ${item.testText}]")
            binding.dogName.text = item.name
            binding.dogName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, com.redpine.core.R.drawable.ic_filter_gender_male, 0
            )
            binding.dogAge.text = item.age
            binding.dogHeight.text = item.testText
        }
    }
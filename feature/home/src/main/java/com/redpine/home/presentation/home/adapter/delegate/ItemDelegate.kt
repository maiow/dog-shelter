package com.redpine.home.presentation.home.adapter.delegate

import android.view.LayoutInflater
import com.redpine.adapter.adapterDelegate
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.databinding.NewsViewHolderBinding
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.domain.model.News
import com.redpine.core.extensions.loadImage
import com.redpine.core.tools.ClickableView

fun newsDelegate(onItemClick: (ClickableView, Item) -> Unit) =
    adapterDelegate<Item, News, NewsViewHolderBinding>(
        { parent ->
            NewsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
    adapterDelegate<Item, Dog, DogViewHolderBinding>({ parent ->
        DogViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }) {
        binding.btnFavorite.setOnClickListener {
            ClickableView.FAVORITE.itemPosition = bindingAdapterPosition
            onItemClick(ClickableView.FAVORITE, item)
        }

        binding.dogCard.setOnClickListener {
            onItemClick(ClickableView.DOG, item)
        }

        bind {
            binding.btnFavorite.isSelected = item.isFavorite
            binding.dogName.text = item.name
            binding.dogAge.text = "Возраст: " + item.age
            binding.dogHeight.text = "Рост в холке: " + item.height + " см"
            binding.dogPhoto.loadImage(item.imageUrl)
//            Log.d(TAG, "dogsDelegate: ")
        }

        bindForPayloads { payloads ->
            binding.btnFavorite.isSelected = payloads.last() as Boolean
        }
    }

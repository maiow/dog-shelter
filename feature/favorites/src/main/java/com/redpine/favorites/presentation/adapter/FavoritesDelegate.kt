package com.redpine.favorites.presentation.adapter

import android.view.LayoutInflater
import com.redpine.adapter.adapterDelegate
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.domain.model.Dog
import com.redpine.core.domain.model.Item
import com.redpine.core.extensions.loadImage
import com.redpine.core.tools.ClickableView

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
        }

        bindForPayloads { payloads ->
            binding.btnFavorite.isSelected = payloads.last() as Boolean
        }
    }

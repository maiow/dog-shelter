package com.redpine.home.presentation.home.delegate

import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.databinding.NewsViewHolderBinding
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.News
import com.redpine.core.tools.ClickableView
import com.redpine.home.R

fun newsDelegate() =
    adapterDelegateViewBinding<News, Item, NewsViewHolderBinding>({ inflater, root ->
        NewsViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.newsTitle.text = item.title
            binding.newsBodyPreview.text = item.body
        }
    }

fun dogsDelegate(onItemClick: (ClickableView, Item) -> Unit) =
    adapterDelegateViewBinding<Dog, Item, DogViewHolderBinding>({ inflater, root ->
        DogViewHolderBinding.inflate(inflater, root, false)
    }) {
        binding.btnFavorite.setOnClickListener {
            ClickableView.FAVORITE.itemPosition = bindingAdapterPosition
            onItemClick(ClickableView.FAVORITE, item)
            /**переделать как-то*/
//            binding.btnFavorite.isSelected = item.isFavorite
            Log.d(TAG, "bindingPosition: ${item.isFavorite}")
        }
        bind {
            binding.btnFavorite.isSelected = item.isFavorite
            binding.dogName.text = item.name
            binding.dogName.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, com.redpine.core.R.drawable.ic_filter_gender_male, 0
            )
            binding.dogAge.text = item.age
            binding.dogHeight.text = item.testText
        }
    }

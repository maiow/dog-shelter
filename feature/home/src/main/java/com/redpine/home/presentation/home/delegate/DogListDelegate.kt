package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.Item
import com.redpine.core.model.card.NewDog
import com.redpine.core.model.card.RecentSeenDog

fun dogListDelegate() =
    adapterDelegateViewBinding<Dog, Item, DogViewHolderBinding>({ inflater, root ->
        DogViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.dogName.text = item.name
            binding.dogAge.text = item.age

            /**здесь is только для теста. потом уберем*/
            if(item is NewDog) binding.dogHeight.text = "Новая собака"
            if(item is RecentSeenDog) binding.dogHeight.text = "Недавно просмотренная"
        }
    }
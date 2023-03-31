package com.redpine.home.presentation.home.delegate

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.redpine.core.databinding.DogViewHolderBinding
import com.redpine.core.model.Dog
import com.redpine.core.model.Item

fun dogListDelegate() =
    adapterDelegateViewBinding<Dog, Item, DogViewHolderBinding>({ inflater, root ->
        DogViewHolderBinding.inflate(inflater, root, false)
    }) {
        bind {
            binding.dogName.text = item.name
            binding.dogAge.text = item.age
        }
    }
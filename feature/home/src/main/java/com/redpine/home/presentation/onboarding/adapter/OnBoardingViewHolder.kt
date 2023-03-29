package com.redpine.home.presentation.onboarding.adapter

import androidx.recyclerview.widget.RecyclerView
import com.redpine.home.databinding.ItemOnBoardingBinding
import com.redpine.home.domain.model.Onboarding

class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Onboarding) {

        binding.image.setImageResource(item.resImgId)
        binding.title.text = item.title
        binding.text.text = item.description

    }
}
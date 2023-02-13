package com.redpine.home.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redpine.home.databinding.ItemOnBoardingBinding
import com.redpine.home.domain.model.Onboarding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingViewHolder>() {

    private var list = listOf<Onboarding>()

    fun setItems(items: List<Onboarding>) {
        list = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OnBoardingViewHolder(
        ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
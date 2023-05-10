package com.redpine.home.presentation.pets_card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redpine.core.tools.loadImage
import com.redpine.home.databinding.CarouselItemContainerBinding

class CarouselAdapter (private val images: List<String>) :
    RecyclerView.Adapter<CarouselAdapter.DogGalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DogGalleryViewHolder(
            CarouselItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: DogGalleryViewHolder, position: Int) =
        holder.bind(images[position])

    override fun getItemCount(): Int = images.size

    class DogGalleryViewHolder(private val binding: CarouselItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) = binding.carouselImageView.loadImage(item)
    }
}
package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.Px
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.redpine.home.Data
import com.redpine.home.HomeBaseFragment
import com.redpine.home.Image
import com.redpine.home.R
import com.redpine.home.databinding.FragmentPetsCardBinding
import com.redpine.home.databinding.ItemGridImageBinding
import com.redpine.home.presentation.home.delegate.HomeAdapter

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }

    private val adapter by lazy { CarouselAdapter(Data.images) }

    private val args by navArgs<PetsCardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
        setShareButton(args.dogId)
        setCuratorButton(args.dogId)

        binding.dogPhoto.adapter = adapter

        binding.dogPhoto
            .addItemDecoration(
                LinearHorizontalSpacingDecoration(
                    resources.getDimensionPixelSize(R.dimen.carousel_spacing)
                )
            )
        PagerSnapHelper().attachToRecyclerView(binding.dogPhoto)

    }

    private fun setCloseButton() = binding.backButton.setOnClickListener {
        findNavController().popBackStack()
    }


    private fun setShareButton(dogId: Int) = binding.shareButton.setOnClickListener {
        shareLinkOnDog(viewModel.getDogLink(dogId))
    }


    private fun setCuratorButton(dogId: Int) = binding.curatorButton.setOnClickListener {
        //callCurator(viewModel.getCuratorNumber(dogId))
        sendWhatsAppMessageToCurator(viewModel.getCuratorNumber(dogId), viewModel.getDogName(dogId))
    }


    private fun shareLinkOnDog(link: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://priut-ks.ru/tproduct/$link")
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
    }

    private fun sendWhatsAppMessageToCurator(phone: String, dogName: String) {
        val message = "Добрый день! Пишу по поводу собаки $dogName из Красной Сосны"
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phone&text=$message")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}

class LinearHorizontalSpacingDecoration(private val innerSpacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {

        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == 0) outRect.left = innerSpacing
        outRect.right = if (state.itemCount == itemPosition) innerSpacing  else innerSpacing


    }
}

internal class CarouselAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<CarouselAdapter.DogGalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogGalleryViewHolder {
        return DogGalleryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DogGalleryViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class DogGalleryViewHolder(private val binding: ItemGridImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Image) {
            Glide.with(binding.root).load(item.url).into(binding.image)
        }

        companion object {
            fun create(parent: ViewGroup): DogGalleryViewHolder {
                return DogGalleryViewHolder(
                    ItemGridImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

}

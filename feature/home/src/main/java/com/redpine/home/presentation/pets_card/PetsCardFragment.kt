package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.redpine.core.tools.loadImage
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.CarouselItemContainerBinding
import com.redpine.home.databinding.FragmentPetsCardBinding
import kotlinx.coroutines.launch

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }
    private val args by navArgs<PetsCardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
        setShareButton(args.dogId)
        setCuratorButton(args.dogId)
        observeData(args.dogId)
        assignCarouselLayoutManager()
    }

    private fun assignCarouselLayoutManager() {
        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager()
    }

    private fun observeData(dogId: Int) {
        viewModel.getDogImages(dogId)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.collect { imagesList ->
                binding.carouselRecyclerView.adapter = CarouselAdapter(imagesList)
            }
        }
    }

    private fun setCloseButton() = binding.backButton.setOnClickListener {
        findNavController().popBackStack()
    }

    private fun setShareButton(dogId: Int) = binding.shareButton.setOnClickListener {
        shareLinkOnDog(viewModel.getDogLink(dogId))
    }

    private fun setCuratorButton(dogId: Int) = binding.curatorButton.setOnClickListener {
        sendWhatsAppMessageToCurator(viewModel.getCuratorNumber(dogId), viewModel.getDogName(dogId))
    }

    private fun shareLinkOnDog(link: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, WEBSITE_LINK + link)
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
    }

    private fun sendWhatsAppMessageToCurator(phone: String, dogName: String) {
        val message = getString(R.string.watsapp_message, dogName)
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$phone&text=$message")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private companion object {
        const val WEBSITE_LINK = "https://priut-ks.ru/tproduct/"
    }
}

internal class CarouselAdapter(private val images: List<String>) :
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
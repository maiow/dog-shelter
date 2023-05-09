package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import com.redpine.core.state.LoadState
import com.redpine.core.tools.loadImage
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.CarouselItemContainerBinding
import com.redpine.home.databinding.FragmentPetsCardBinding
import com.redpine.home.presentation.pets_card.PetsCardViewModel.State.Error
import com.redpine.home.presentation.pets_card.PetsCardViewModel.State.Loaded
import com.redpine.home.presentation.pets_card.PetsCardViewModel.State.Loading

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }
    private val args by navArgs<PetsCardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGettingArgument(args.dogId)
        setCloseButton()
        setShareButton(args.dogId)
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.imagesList) { imagesList ->
            binding.carouselRecyclerView.adapter = CarouselAdapter(imagesList)
        }
        flowObserver(viewModel.dogInfoState) { data -> bindDogInfo(data) }
        assignCarouselLayoutManager()
    }

    private fun assignCarouselLayoutManager() {
        binding.carouselRecyclerView.layoutManager = CarouselLayoutManager()
    }

    private fun loadingObserve(loadState: LoadState) {
        binding.commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
        binding.carouselRecyclerView.isVisible = (loadState == LoadState.SUCCESS)
        if (LoadState.ERROR_NETWORK == loadState) Toast.makeText(
            requireContext(), "loading error", Toast.LENGTH_SHORT
        ).show()
    }

    private fun bindDogInfo(state: PetsCardViewModel.State) {
        binding.commonProgress.progressBar.isVisible = (state == Loading)

        if (state is Loaded) {
            binding.dogsName.text = state.dogInfo.name
            binding.age.text = state.dogInfo.age
            binding.color.text = state.dogInfo.color
            binding.height.text = state.dogInfo.height.toString() + " см"
            binding.story.text = state.dogInfo.text
            val genderIcon = if (state.dogInfo.gender == GENDER_MALE)
                ResourcesCompat.getDrawable(
                    resources,
                    com.redpine.core.R.drawable.ic_filter_gender_male,
                    null
                ) else ResourcesCompat.getDrawable(
                resources, com.redpine.core.R.drawable.ic_filter_gender_female, null
            )
            binding.genderImage.setImageDrawable(genderIcon)
            setCuratorButton(state.dogInfo.curator_phone, state.dogInfo.name)
        }
        if (state is Error) {
            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCloseButton() = binding.backButton.setOnClickListener {
        findNavController().popBackStack()
    }

    private fun setShareButton(dogId: Int) = binding.shareButton.setOnClickListener {
        shareLinkOnDog(viewModel.getDogLink(dogId))
    }

    private fun setCuratorButton(curator_phone: String, dogName: String) =
        binding.curatorButton.setOnClickListener {
            sendWhatsAppMessageToCurator(curator_phone, dogName)
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
        const val GENDER_MALE = "male"
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
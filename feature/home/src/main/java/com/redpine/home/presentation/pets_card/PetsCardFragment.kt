package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.carousel.CarouselLayoutManager
import com.redpine.core.domain.model.Dog
import com.redpine.core.state.LoadState
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentPetsCardBinding

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }
    private val args by navArgs<PetsCardFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGettingArgument(args.dog)
        showDogInfo(args.dog)
        setCloseButton()
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.imagesList) { imagesList ->
            binding.carouselRecyclerView.adapter = CarouselAdapter(imagesList)
        }
        setCuratorButton(args.dog.curatorPhone, args.dog.name)
        setShareButton(args.dog.webLink)
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

    private fun showDogInfo(dog: Dog) {
        binding.dogsName.text = dog.name
        binding.age.text = dog.age
        binding.color.text = dog.color
        binding.height.text = getString(R.string.height, dog.height)
        binding.story.text = dog.text
        val genderIcon = if (dog.gender == GENDER_MALE)
            ResourcesCompat.getDrawable(
                resources,
                com.redpine.core.R.drawable.ic_filter_gender_male,
                null
            ) else ResourcesCompat.getDrawable(
            resources, com.redpine.core.R.drawable.ic_filter_gender_female, null
        )
        binding.genderImage.setImageDrawable(genderIcon)
    }

    private fun setCloseButton() = binding.backButton.setOnClickListener {
        findNavController().popBackStack()
    }

    private fun setShareButton(dogLink: String) = binding.shareButton.setOnClickListener {
        shareLinkOnDog(dogLink)
    }

    private fun setCuratorButton(phone: String, dogName: String) =
        binding.curatorButton.setOnClickListener {
            sendWhatsAppMessageToCurator(phone, dogName)
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
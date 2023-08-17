package com.redpine.home.presentation.pets_card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.google.android.material.carousel.CarouselLayoutManager
import com.redpine.core.domain.model.Dog
import com.redpine.core.extensions.onClickToPopBackStack
import com.redpine.core.state.LoadState
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentPetsCardBinding

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    private val args by navArgs<PetsCardFragmentArgs>()
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGettingArgument(args.dog)
        setUserInterface(args.dog)
        flowObserver(viewModel.loadState) { loadState -> loadingObserve(loadState) }
        flowObserver(viewModel.dogInfo) { dog -> loadingDogInfo(dog) }
        flowObserver(viewModel.imagesList) { imagesList ->
            binding.carouselRecyclerView.adapter = CarouselAdapter(imagesList)
        }
        flowObserver(viewModel.isNavigateAuth) { action -> observeNavigateAuth(action) }
    }

    private fun observeNavigateAuth(isNavigation: Boolean) {
        if (isNavigation) {
            showDialog(com.redpine.core.R.string.auth_dialog_message) { navigate(R.id.auth_nav_graph) }
            viewModel.resetNavigateFlow()
        }
    }

    private fun loadingDogInfo(dog: Dog) {
        with(binding) {
            dogsName.text = dog.name
            age.text = dog.age
            color.text = dog.color
            height.text = getString(R.string.height, dog.height)
            story.text = dog.text.replace("<br />", "\n")
                .replace("""\"""", "\"")
            genderImage.isSelected = dog.isMale
            likeButton.isSelected = dog.isFavorite
            likeButton.setOnClickListener {
                viewModel.addToFavorites(dog)
            }
        }
    }

    private fun setUserInterface(dog: Dog) {
        with(binding) {
            backButton.onClickToPopBackStack()
            curatorButton.setOnClickListener {
                sendWhatsAppMessageToCurator(dog.curatorPhone, dog.name)
            }
            shareButton.setOnClickListener { shareLinkOnDog(dog.webLink) }
            carouselRecyclerView.layoutManager = CarouselLayoutManager()
        }
    }

    private fun loadingObserve(loadState: LoadState) {
        with(binding) {
            commonProgress.progressBar.isVisible = loadState == LoadState.LOADING
            carouselRecyclerView.isVisible = (loadState == LoadState.SUCCESS)
            connectionError.error.isVisible = loadState == LoadState.ERROR_NETWORK
            connectionError.retryButton.setOnClickListener {
                viewModel.onGettingArgument(args.dog)
            }
        }
    }

    private fun shareLinkOnDog(link: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, WEBSITE_LINK + link)
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)))
    }

    private fun sendWhatsAppMessageToCurator(phone: String, dogName: String) {
        val message = getString(R.string.watsapp_message, dogName)
        val uri = Uri.parse(WHATSAPP_URI + phone + WHATSAPP_URI_TEXT + message)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private companion object {
        const val WEBSITE_LINK = "https://priut-ks.ru/tproduct/"
        const val WHATSAPP_URI = "https://api.whatsapp.com/send?phone="
        const val WHATSAPP_URI_TEXT = "&text="
    }
}
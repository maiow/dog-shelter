package com.redpine.home.presentation.pets_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentPetsCardBinding

class PetsCardFragment : HomeBaseFragment<FragmentPetsCardBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentPetsCardBinding.inflate(inflater)
    private val viewModel: PetsCardViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
    }

    private fun setCloseButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
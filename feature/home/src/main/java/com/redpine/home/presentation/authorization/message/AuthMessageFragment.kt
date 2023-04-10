package com.redpine.home.presentation.authorization.message

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentAuthMessageBinding

class AuthMessageFragment : HomeBaseFragment<FragmentAuthMessageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentAuthMessageBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
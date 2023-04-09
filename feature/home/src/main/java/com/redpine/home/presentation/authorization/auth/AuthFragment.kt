package com.redpine.home.presentation.authorization.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentAuthBinding

class AuthFragment : HomeBaseFragment<FragmentAuthBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    private val viewModel:AuthViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.authButton.setOnClickListener{
            viewModel.startAuth(binding.editEmail.text.toString(),binding.editPassword.text.toString())
        }
    }

}
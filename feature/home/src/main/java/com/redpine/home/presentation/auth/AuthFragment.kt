package com.redpine.home.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch

class AuthFragment : HomeBaseFragment<FragmentAuthBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentAuthBinding.inflate(inflater)

    private val viewModel by lazy { initViewModel<AuthViewModel>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickAuth()

        clickCreate()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.info.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clickCreate() {
        binding.create.setOnClickListener {
            viewModel.createUser(binding.email.text.toString(), binding.password.text.toString())
            binding.verifiedText.text = "Проверь почту"
        }
    }

    private fun clickAuth() {
        binding.auth.setOnClickListener {
            viewModel.authUser(binding.email.text.toString(), binding.password.text.toString())
            Log.e("Kart","test =  ${viewModel.emailVerified()}")
            binding.verifiedText.text = if (!viewModel.emailVerified())
                "Вошли" else "Проверь почту"
        }
    }

}
package com.redpine.home.presentation.authorization.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.core.extensions.onClickToPopBackStack
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentAuthMessageBinding

class AuthMessageFragment : HomeBaseFragment<FragmentAuthMessageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentAuthMessageBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authButton.onClickToPopBackStack()
    }
}
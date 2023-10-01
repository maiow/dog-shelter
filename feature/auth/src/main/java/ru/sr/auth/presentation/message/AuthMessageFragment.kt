package ru.sr.auth.presentation.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.auth.databinding.FragmentAuthMessageBinding
import com.redpine.core.extensions.onClickToPopBackStack
import ru.sr.auth.presentation.AuthBaseFragment

class AuthMessageFragment : AuthBaseFragment<FragmentAuthMessageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentAuthMessageBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.authButton.onClickToPopBackStack()
    }
}
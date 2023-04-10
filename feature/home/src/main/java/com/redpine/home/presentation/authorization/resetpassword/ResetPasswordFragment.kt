package com.redpine.home.presentation.authorization.resetpassword

import android.view.LayoutInflater
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentRegistrationBinding

class ResetPasswordFragment : HomeBaseFragment<FragmentRegistrationBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentRegistrationBinding.inflate(inflater)
}
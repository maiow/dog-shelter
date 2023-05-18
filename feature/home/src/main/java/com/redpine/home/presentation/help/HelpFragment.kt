package com.redpine.home.presentation.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.FragmentHelpBinding

class HelpFragment : HomeBaseFragment<FragmentHelpBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentHelpBinding.inflate(inflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            resources.getColor(com.redpine.core.R.color.F9_background, null)
    }
}
package com.redpine.profile.presentation.help

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.profile.ProfileBaseFragment
import com.redpine.profile.databinding.FragmentHelpBinding

class HelpFragment : ProfileBaseFragment<FragmentHelpBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHelpBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor =
            resources.getColor(com.redpine.core.R.color.F9_background, null)

        with(binding) {
            imageView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://priut-ks.ru/contacts")))
            }
            imageView2.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://priut-ks.ru/contacts")))
            }
            imageView3.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://priut-ks.ru/fincurator")))
            }
            imageView4.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://priut-ks.ru/howtohelp")))
            }
            imageView5.setOnClickListener {
                helpScrollView.fullScroll(View.FOCUS_DOWN)
            }
            imageView6.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://priut-ks.ru/contacts")))
            }
        }
    }
}
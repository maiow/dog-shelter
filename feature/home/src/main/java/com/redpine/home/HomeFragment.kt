package com.redpine.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.redpine.api.Api
import com.redpine.core.base.BaseFragment
import com.redpine.home.databinding.FragmentHomeBinding
import com.redpine.home.di.HomeComponentViewModel
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)

@Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<HomeViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
          getComponentViewModel<HomeComponentViewModel>(this).homeComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("Kart",viewModel.getText())
    }

}
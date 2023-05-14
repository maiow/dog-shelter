package com.redpine.dogshelter.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.redpine.dogshelter.R
import com.redpine.dogshelter.app.App
import com.redpine.dogshelter.databinding.FragmentMainBinding
import javax.inject.Inject

class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireContext().applicationContext as App).appComponent.inject(this)
        viewModel  = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val destinationsInBackStack =
            findNavController().backQueue.joinToString("\n") { dest ->
                dest.destination.displayName
            }
        Log.d("BackStack", "----------------------------------\n$destinationsInBackStack")
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isOnboardingShown)
            findNavController().navigate(R.id.action_mainFragment_to_home_nav_graph)
        else findNavController().navigate(R.id.action_mainFragment_to_on_boarding_nav_graph)

    }
}
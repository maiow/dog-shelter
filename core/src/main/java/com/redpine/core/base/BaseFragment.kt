package com.redpine.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    lateinit var factory: ViewModelProvider.Factory

    protected abstract fun initBinding(inflater: LayoutInflater): B?

    protected inline fun <reified C : ComponentViewModel> initViewModelFactory(
        owner: ViewModelStoreOwner
    ) {
        factory = ViewModelProvider(owner)[C::class.java].moduleComponent.viewModelFactory
    }

    inline fun <reified VM : ViewModel> initViewModel(): VM {
        val viewModel by viewModels<VM> { factory }
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = initBinding(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
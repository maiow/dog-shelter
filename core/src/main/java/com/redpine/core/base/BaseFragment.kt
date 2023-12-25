package com.redpine.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract fun initBinding(inflater: LayoutInflater): B?

    @Suppress("UNREACHABLE_CODE")
    open fun initViewModelFactory(): ViewModelProvider.Factory {
        return throw IllegalAccessError("error create viewModel")
    }

    inline fun <reified VM : ViewModel> initViewModel(): VM {
        val viewModel by viewModels<VM>() { initViewModelFactory() }
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

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun navigate(id: Int) {
        findNavController().navigate(id)
    }


    protected fun <I : Any?> flowObserver(flow: Flow<I>?, action: suspend (it: I) -> Unit) =
        viewLifecycleOwner.lifecycleScope.launch {
            flow?.collect {
                action(it)
            }
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

abstract class BaseFragmentWithViewModel<B : ViewBinding,V:ViewModel> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    @Inject
    lateinit var viewModel: V

    protected abstract fun initBinding(inflater: LayoutInflater): B?
    protected abstract fun inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        inject()
        _binding = initBinding(inflater)
        return binding.root
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun navigate(id: Int) {
        findNavController().navigate(id)
    }


    protected fun <I : Any?> flowObserver(flow: Flow<I>?, action: suspend (it: I) -> Unit) =
        viewLifecycleOwner.lifecycleScope.launch {
            flow?.collect {
                action(it)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
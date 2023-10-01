package com.redpine.core.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.redpine.core.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected abstract fun initBinding(inflater: LayoutInflater): B?

    abstract fun initViewModelFactory(): ViewModelProvider.Factory


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

    protected fun showDialog(messageId: Int, onApplyClick: () -> Unit) {
        val authDialog = MaterialAlertDialogBuilder(requireContext()).create()
        authDialog.apply {
            setMessage(getString(messageId))
            setButton(
                AlertDialog.BUTTON_NEGATIVE, getString(R.string.auth_dialog_cancel)
            ) { dialog, _ ->
                dialog.dismiss()
            }
            setButton(
                AlertDialog.BUTTON_POSITIVE, getString(R.string.auth_dialog_apply)
            ) { _, _ ->
                Log.e("kart","Apllay")
                onApplyClick()
            }
            show()
        }
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
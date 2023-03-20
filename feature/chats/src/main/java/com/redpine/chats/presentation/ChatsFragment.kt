package com.redpine.chats.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.redpine.chats.ChatsBaseFragment
import com.redpine.chats.R
import com.redpine.chats.databinding.FragmentChatsBinding

class ChatsFragment : ChatsBaseFragment<FragmentChatsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentChatsBinding.inflate(inflater)
    private val viewModel: ChatsViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = viewModel.getText()
        getMessages()
    }

    private fun getMessages() {
        Log.d(TAG, "getMessages: ${viewModel.getMessages()[0].author}")
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        viewModel.getMessages().forEach { message ->
            val mView = MessageView(requireContext())
            if (message.author == "me") {
                params.gravity = Gravity.END
                mView.layoutParams = params
            }
            mView.binding.author.text = message.author
            mView.binding.body.text = message.body
            binding.chat.addView(mView)
        }
    }
}
package com.redpine.chats.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.redpine.chats.ChatsBaseFragment
import com.redpine.chats.R
import com.redpine.chats.databinding.FragmentChatsBinding
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChatsFragment : ChatsBaseFragment<FragmentChatsBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentChatsBinding.inflate(inflater)
    private val viewModel: ChannelViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Step 3 - Authenticate and connect the user
//        val user = User(
//            id = "VSV",
//            name = "VSV",
//            image = "https://bit.ly/2TIt8NR"
//        )

//        val user = User(
//            id = "redpet",
//            name = "redpet",
//            image = "https://bit.ly/3n5SWWK"
//        )
//        val user = User(
//            id = "bender",
//            name = "Bender",
//            image = "https://bit.ly/321RmWb",//"https://bit.ly/3TE4Tiz"
//        )

        // viewModel.connectUser(user)

        // Step 4 - Set the channel list filter and order
        // This can be read as requiring only channels whose "type" is "messaging" AND
        // whose "members" include our "user.id"
//        val filter = Filters.and(
//            Filters.eq("type", "messaging") //,
//            //Filters.`in`("members", listOf(user.id))
//        )

        val viewModelFactory =
            ChannelListViewModelFactory(/*filter, */null, ChannelListViewModel.DEFAULT_SORT)
        val clViewModel: ChannelListViewModel by viewModels { viewModelFactory }

        // Step 5 - Connect the ChannelListViewModel to the ChannelListView, loose
        //          coupling makes it easy to customize
        clViewModel.bindView(binding.channelListView, this)
        binding.channelListView.setChannelItemClickListener { channel ->
            findNavController().navigate(
                R.id.action_chatsFragment_to_singleChatFragment,
                Bundle().apply { putString("channelId", channel.cid) }
            )
        }

        val channelListHeaderViewModel: ChannelListHeaderViewModel by viewModels()
        channelListHeaderViewModel.bindView(binding.channelListHeaderView, viewLifecycleOwner)

        binding.channelListHeaderView.setOnUserAvatarClickListener {
            viewModel.logout()
            findNavController().popBackStack()
        }

        binding.channelListHeaderView.setOnActionButtonClickListener {
            val dialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_channel_name, null, false)
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.choose_channel_name)
                .setView(dialogView)
                .setPositiveButton(R.string.create) { _, _ ->
                    viewModel.createChannel(
                        dialogView
                            .findViewById<TextInputEditText>(R.id.etChannelName).text.toString()
                    )
                }
                .setNegativeButton(R.string.cancel) { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .create()
                .show()
        }


        lifecycleScope.launchWhenStarted {
            viewModel.createChannelEvent.collect { event ->
                when (event) {
                    is ChannelViewModel.CreateChannelEvent.Error -> {
                        Toast.makeText(
                            requireContext(),
                            event.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is ChannelViewModel.CreateChannelEvent.Success -> {
                        Toast.makeText(
                            requireContext(),
                            R.string.channel_created,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
}

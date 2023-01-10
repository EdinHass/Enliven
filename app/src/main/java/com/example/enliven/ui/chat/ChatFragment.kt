package com.example.enliven.ui.chat

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentChatBinding
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryChannelRequest
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.MessageListHeaderView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding
    private lateinit var currentUser: FirebaseUser
    private lateinit var activityMain: AppCompatActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
        currentUser = FirebaseAuth.getInstance().currentUser ?: return

        (activity as AppCompatActivity).supportActionBar?.hide()

        val currentUsersId = currentUser.uid
        val recipientsId = arguments?.getString(RECIPIENT_ID) ?: return
        val channelId = arguments?.getString(CHANNEL_ID) ?: return


        if(channelId=="n/a") {
            ChatClient.instance().createChannel(
                channelType = "messaging",
                channelId = "",
                memberIds = listOf(currentUsersId, recipientsId),
                extraData = emptyMap()
            ).enqueue {
                if (it.isSuccess) {

                    val channel = it.data()
                    initChat(channel)
                } else {
                    Log.e("ERROR", "${it.error().message}")
                }
            }
        }else{
            ChatClient.instance().queryChannel("messaging", channelId,  QueryChannelRequest()).enqueue {
                if(it.isSuccess){
                    val channel = it.data()
                    initChat(channel)
                }else{
                    Log.e("ERROR", "${it.error().message}")
                }
            }
        }


    }

    private fun initChat(channel: Channel) {
        val factory = MessageListViewModelFactory("${channel.type}:${channel.id}")
        val messageListHeaderViewModel: MessageListHeaderViewModel by viewModels { factory }
        val messageListViewModel: MessageListViewModel by viewModels { factory }
        val messageInputViewModel: MessageInputViewModel by viewModels { factory }

        messageListHeaderViewModel.bindView(binding.messageListHeaderView, this)
        messageListViewModel.bindView(binding.messageListView, this)
        messageInputViewModel.bindView(binding.messageInputView, this)

        messageListViewModel.mode.observe(viewLifecycleOwner){ mode ->
            when(mode){
                is MessageListViewModel.Mode.Thread -> {
                    messageListHeaderViewModel.setActiveThread(mode.parentMessage)
                    messageInputViewModel.setActiveThread(mode.parentMessage)
                }
                MessageListViewModel.Mode.Normal -> {
                    messageListHeaderViewModel.resetThread()
                    messageInputViewModel.resetThread()
                }
            }
        }

        binding.messageListView.setMessageEditHandler(messageInputViewModel::postMessageToEdit)

        messageListViewModel.state.observe(viewLifecycleOwner) { state ->
            if (state is MessageListViewModel.State.NavigateUp) {
                finish()
            }
        }

        // Step 6 - Handle back button behaviour correctly when you're in a thread
        val backHandler = {
            messageListViewModel.onEvent(MessageListViewModel.Event.BackButtonPressed)
        }
        binding.messageListHeaderView.setBackButtonClickListener(backHandler)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            backHandler()
        }

    }

    private fun finish() {
        (activity as AppCompatActivity).supportActionBar?.show()
        findNavController().navigate(R.id.channelsFragment)
    }

    companion object{
        const val RECIPIENT_ID = "recipients_id"
        const val CHANNEL_ID = "channel_id"
    }
}
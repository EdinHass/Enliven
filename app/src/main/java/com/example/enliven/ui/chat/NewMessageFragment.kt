package com.example.enliven.ui.chat

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentNewMessageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters

class NewMessageFragment : Fragment(R.layout.fragment_new_message) {
    private lateinit var binding: FragmentNewMessageBinding
    private lateinit var currentUser: FirebaseUser
    private val adapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewMessageBinding.bind(view)
        activity?.closeOptionsMenu()
        (activity as AppCompatActivity).supportActionBar?.setTitle("Enliven Social")
        (activity as AppCompatActivity).supportActionBar?.hide()
        currentUser = FirebaseAuth.getInstance().currentUser ?: return
        binding.recyclerViewUsers.adapter = adapter
        getUsers()
        adapter.itemClickListener = {item, position, _ ->
            findNavController().navigate(R.id.action_newMessageFragment_to_chatFragment, bundleOf(ChatFragment.RECIPIENT_ID to item.id,
            ChatFragment.CHANNEL_ID to "n/a"))
        }
        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_newMessageFragment_to_channelsFragment2)
        }

    }

    private fun getUsers(){

        val request = QueryUsersRequest(
            filter = Filters.and(
                Filters.ne("id", currentUser.uid),
                Filters.eq("role", "user")
                ),
            offset = 0,
            limit = 100
        )
        ChatClient.instance().queryUsers(request).enqueue {
            if(it.isSuccess){
                adapter.submitList(it.data())
            }else{
                Log.e("ERROR", it.error().message!!)
            }
        }
    }

}
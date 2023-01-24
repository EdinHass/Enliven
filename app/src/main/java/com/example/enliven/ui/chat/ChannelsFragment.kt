package com.example.enliven.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentChannelsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChannelsFragment : Fragment(R.layout.fragment_channels){
    private lateinit var binding: FragmentChannelsBinding
    private lateinit var currentUser: FirebaseUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChannelsBinding.bind(view)
        val dl: DrawerLayout? = activity?.findViewById<DrawerLayout>(R.id.drawer_layout);
        dl?.setDrawerLockMode(LOCK_MODE_UNLOCKED)
        currentUser = FirebaseAuth.getInstance().currentUser ?: return
        (activity as AppCompatActivity).supportActionBar?.title = "Enliven Social"
        (activity as AppCompatActivity).supportActionBar?.show()

        val filter = Filters.and(
            Filters.eq("type", "messaging"),
            Filters.`in`("members", listOf(currentUser.uid))
        )
        val viewModelFactory = ChannelListViewModelFactory(filter, ChannelListViewModel.DEFAULT_SORT)
        val viewModel: ChannelListViewModel by viewModels { viewModelFactory }



        viewModel.bindView(binding.channelListView, this)
        binding.channelListView.setChannelItemClickListener { channel ->
            findNavController().navigate(R.id.chatFragment, bundleOf(ChatFragment.CHANNEL_ID to channel.id,
            ChatFragment.RECIPIENT_ID to "n/a"))
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }





}
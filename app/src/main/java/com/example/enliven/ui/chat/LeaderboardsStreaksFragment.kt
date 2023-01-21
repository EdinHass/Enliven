package com.example.enliven.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.FragmentLeaderboardsStreaksBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters

class LeaderboardsStreaksFragment : Fragment(R.layout.fragment_leaderboards_streaks) {
    private lateinit var binding: FragmentLeaderboardsStreaksBinding
    private lateinit var currentUser: FirebaseUser
    private val adapter = LeaderboardsStreaksAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLeaderboardsStreaksBinding.bind(view)
        activity?.closeOptionsMenu()
        (activity as AppCompatActivity).supportActionBar?.setTitle("Streaks Leaderboards")
        currentUser = FirebaseAuth.getInstance().currentUser ?: return
        binding.recyclerViewUsers.adapter = adapter
        getUsers()
    }

    private fun getUsers(){
        val request = QueryUsersRequest(
            filter = Filters.and(
                Filters.eq("role", "user"),
            ),
            offset = 0,
            limit = 100
        )
        ChatClient.instance().queryUsers(request).enqueue {
            if(it.isSuccess){
                adapter.submitList(it.data().sortedByDescending { it.getExtraValue(UserExtra.STREAK, 0).toInt() })
            }else{
                Log.e("ERROR", it.error().message!!)
            }
        }
    }

}
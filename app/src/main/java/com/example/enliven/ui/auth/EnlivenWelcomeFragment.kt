package com.example.enliven.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentEnlivenWelcomeBinding

class EnlivenWelcomeFragment : Fragment(R.layout.fragment_enliven_welcome) {
    private lateinit var binding: FragmentEnlivenWelcomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnlivenWelcomeBinding.bind(view)

        binding.buttonnext1.setOnClickListener(){
            findNavController().navigate(R.id.action_enlivenWelcomeFragment_to_enlivenInfoFragment1)
        }

    }
}
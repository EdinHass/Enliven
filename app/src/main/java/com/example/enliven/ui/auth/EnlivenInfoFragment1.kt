package com.example.enliven.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentEnlivenInfo1Binding

class EnlivenInfoFragment1 : Fragment(R.layout.fragment_enliven_info1) {
    private lateinit var binding: FragmentEnlivenInfo1Binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnlivenInfo1Binding.bind(view)
        binding.buttonback1.setOnClickListener(){
            findNavController().navigate(R.id.action_enlivenInfoFragment1_to_enlivenWelcomeFragment)
        }
        binding.buttonnext1.setOnClickListener(){
            findNavController().navigate(R.id.action_enlivenInfoFragment1_to_enlivenInfoFragment2)
        }

    }
}
package com.example.enliven.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentEnlivenInfo1Binding
import com.example.enliven.databinding.FragmentEnlivenInfo2Binding

class EnlivenInfoFragment2 : Fragment(R.layout.fragment_enliven_info2) {
    private lateinit var binding: FragmentEnlivenInfo2Binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnlivenInfo2Binding.bind(view)
        binding.buttonback1.setOnClickListener(){
            findNavController().navigate(R.id.action_enlivenInfoFragment2_to_enlivenInfoFragment1)
        }
        binding.buttonnext1.setOnClickListener(){
            findNavController().navigate(R.id.action_enlivenInfoFragment2_to_enterPhoneFragment)
        }

    }
}
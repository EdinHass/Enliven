package com.example.enliven.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentEnterPhoneBinding

class EnterPhoneFragment: Fragment(R.layout.fragment_enter_phone) {

    private lateinit var binding: FragmentEnterPhoneBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterPhoneBinding.bind(view)
        binding.buttonNext.isEnabled = false
        val adapter = ArrayAdapter<Country>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            requireContext().getCountries()
        )

        binding.spinnerCountries.adapter = adapter

        binding.editTextPhone.addTextChangedListener {
            if(!it.isNullOrBlank())
                binding.buttonNext.isEnabled = it.length >=9
        }

        binding.buttonNext.setOnClickListener {
            val country = binding.spinnerCountries.selectedItem as Country
            val phone = binding.editTextPhone.text.toString()
            val phoneNumber = "+${country.phoneCode}$phone"

            findNavController().navigate(R.id.verifyPhoneFragment,
                bundleOf(VerifyPhoneFragment.KEY_PHONE to phoneNumber)
            )
        }
    }
}
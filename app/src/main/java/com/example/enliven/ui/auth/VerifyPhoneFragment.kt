package com.example.enliven.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentVerifyPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerifyPhoneFragment: Fragment(R.layout.fragment_verify_phone) {

    private lateinit var binding: FragmentVerifyPhoneBinding
    private lateinit var auth: FirebaseAuth
    private var verificationId: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVerifyPhoneBinding.bind(view)
        binding.buttonNext.isEnabled = false
        auth = FirebaseAuth.getInstance()

        val phone = arguments?.getString(KEY_PHONE) ?: return

        val options = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phone)
            .setTimeout(120, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        binding.editTextOtp.addTextChangedListener {
            binding.buttonNext.isEnabled = it?.length == 6
        }

        binding.buttonNext.setOnClickListener {
            val otp = binding.editTextOtp.text.toString().trim()
            val credential = PhoneAuthProvider.getCredential(verificationId, otp)
            signInWithCredentials(credential)
        }
    }

    private val callbacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithCredentials(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.e("ERROR", e.message!!)
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_LONG).show()
        }

        override fun onCodeSent(verificationId: String, resendToken: PhoneAuthProvider.ForceResendingToken) {
            this@VerifyPhoneFragment.verificationId = verificationId
        }
    }

    private fun signInWithCredentials(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                findNavController().navigate(R.id.action_verifyPhoneFragment_to_setupProfileFragment)
            }else{
                val exc = task.exception
                Log.e("ERROR", task.exception?.message!!)
                Toast.makeText(requireContext(), exc?.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object{
        const val KEY_PHONE = "key_phone"
    }
}
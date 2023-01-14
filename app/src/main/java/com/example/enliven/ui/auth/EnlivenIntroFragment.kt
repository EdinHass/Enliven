package com.example.enliven.ui.auth

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.enliven.R
import com.example.enliven.databinding.FragmentEnlivenIntroBinding

class EnlivenIntroFragment : Fragment(R.layout.fragment_enliven_intro) {
    private lateinit var binding: FragmentEnlivenIntroBinding
    private val mHandler: Handler = Handler.createAsync(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = activity?.supportFragmentManager
        binding = FragmentEnlivenIntroBinding.bind(view)
        val animFloatUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_bottom)
        val animFloatDown = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_out_top)
        binding.textUp.startAnimation(animFloatDown)
        binding.textDown.startAnimation(animFloatUp)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_enlivenIntroFragment_to_enlivenWelcomeFragment)
        }, 1000)



    }
}
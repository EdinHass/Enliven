package com.example.enliven.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.enliven.R
import com.example.enliven.data.StreamTokenApi
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.FragmentSetupProfileBinding
import com.example.enliven.ui.chat.ChatActivity
import com.example.enliven.ui.snackbar
import com.example.enliven.ui.startNewActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile){
    private lateinit var binding: FragmentSetupProfileBinding
    private lateinit var currentUser: FirebaseUser
    private val streamApi = StreamTokenApi()
    private val tokenProvider = StreamTokenProvider(streamApi)
    private var chosenImage = UserExtra.DEFAULT_AVATAR

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)
        binding.buttonNext.isEnabled = false
        binding.editTextName.addTextChangedListener {
            binding.buttonNext.isEnabled = it?.isNotEmpty() == true
        }

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.imageavatar.setImageURI(result.data?.data)
                chosenImage = result.data?.data.toString()
            }
        }

        binding.frameLayout.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        currentUser = FirebaseAuth.getInstance().currentUser ?: return

        binding.buttonNext.setOnClickListener {
            setupProfile()
        }


    }

    private fun setupProfile() {
        val user = User(
            currentUser.uid,
            extraData = mutableMapOf(
                UserExtra.NAME to binding.editTextName.text.toString().trim(),
                UserExtra.PHONE to currentUser.phoneNumber!!,
                UserExtra.IMAGE to chosenImage
            )
        )

        val sharedPreference = requireActivity().getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("loginToken", tokenProvider.getTokenProvider(currentUser.uid).loadToken())
        editor.putString("loginName", binding.editTextName.text.toString().trim())
        editor.putString("loginPhone", currentUser.phoneNumber!!)
        editor.putString("loginImage", UserExtra.DEFAULT_AVATAR)
        editor.apply()

        ChatClient
            .instance()
            .connectUser(user, sharedPreference.getString("loginToken","")!!)
            .enqueue {  result ->
                if(result.isSuccess){
                    //User Connected to the BAckend Successfully
                    requireActivity().startNewActivity(ChatActivity::class.java)
                }else{
                    snackbar("${result.error().message}")
                }
            }
    }

}
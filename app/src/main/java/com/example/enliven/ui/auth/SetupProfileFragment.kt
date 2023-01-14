package com.example.enliven.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.enliven.R
import com.example.enliven.data.ImageURLS
import com.example.enliven.data.StreamTokenApi
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.FragmentSetupProfileBinding
import com.example.enliven.ui.chat.ChatActivity
import com.example.enliven.ui.snackbar
import com.example.enliven.ui.startNewActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import java.net.SocketTimeoutException

class SetupProfileFragment : Fragment(R.layout.fragment_setup_profile){
    private lateinit var binding: FragmentSetupProfileBinding
    private lateinit var currentUser: FirebaseUser
    private val streamApi = StreamTokenApi()
    private val tokenProvider = StreamTokenProvider(streamApi)
    private var chosenImage = UserExtra.DEFAULT_AVATAR
    val sharedPreference = activity?.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetupProfileBinding.bind(view)
        binding.buttonNext.isEnabled = false
        binding.editTextName.addTextChangedListener {
            binding.buttonNext.isEnabled = it?.isNotEmpty() == true
        }

        Picasso.get().load(getImageEmotion()).into(binding.imageavatar)

        currentUser = FirebaseAuth.getInstance().currentUser ?: return

        binding.buttonNext.setOnClickListener {
            binding.buttonNext.visibility = View.GONE
            binding.loadingImg.visibility = View.VISIBLE
            Thread.sleep(5);
            for(tries in 0..3)
                try{
                    setupProfile()
                    break
                }catch (exception: SocketTimeoutException){
                    Log.e("ERROR", exception.message!!)
                }
        }
    }

    private fun setupProfile() {
        val user = User(
            currentUser.uid,
            extraData = mutableMapOf(
                UserExtra.NAME to binding.editTextName.text.toString().trim(),
                UserExtra.PHONE to currentUser.phoneNumber!!,
                UserExtra.IMAGE to getImageEmotion()!!
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
                    requireActivity().startNewActivity(ChatActivity::class.java)
                }else{
                    Log.e("ERROR", result.error().message!!);
                    snackbar("${result.error().message}")
                }
            }
    }

    fun getImageEmotion(): String? {
        var emotion: String? = null
        var i = 5
        while (emotion == null && i > 0) {
            emotion = sharedPreference?.getString("lastEmotion$i", null)
            i--
        }
            when (emotion) {
                "sad" -> ImageURLS.SAD
                "hap" -> ImageURLS.HAPPY
                "ang" -> ImageURLS.ANGRY
                "anx" -> ImageURLS.ANXIOUS
                "sca" -> ImageURLS.SCARED
                "str" -> ImageURLS.STRESSED
                else -> UserExtra.DEFAULT_AVATAR
            }
        return UserExtra.DEFAULT_AVATAR
    }

}
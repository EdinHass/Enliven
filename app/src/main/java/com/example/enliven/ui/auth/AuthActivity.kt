package com.example.enliven.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.enliven.R
import com.google.firebase.auth.FirebaseAuth
import io.getstream.chat.android.client.ChatClient

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.title = "Enliven Social"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if(FirebaseAuth.getInstance().currentUser != null) {
            FirebaseAuth.getInstance().signOut()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if(FirebaseAuth.getInstance().currentUser != null) {
            FirebaseAuth.getInstance().signOut()
        }
        finish()
        return super.onSupportNavigateUp()
    }
}
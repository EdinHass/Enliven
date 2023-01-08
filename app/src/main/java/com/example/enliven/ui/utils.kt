package com.example.enliven.ui

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <A: Activity>Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also{
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(it)
        finishAndRemoveTask();
    }
}

fun Fragment.snackbar(message: String, retry:(()->Unit)? = null){
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).also{
        it.setAction("Ok"){
            retry?.invoke()
        }
    }
}
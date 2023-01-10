package com.example.enliven.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.os.Build
import android.provider.MediaStore
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



    fun getRoundedShape(scaleBitmapImage: Bitmap): Bitmap? {
        // TODO Auto-generated method stub
        val targetWidth = 50
        val targetHeight = 50
        val targetBitmap = Bitmap.createBitmap(
            targetWidth,
            targetHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(targetBitmap)
        val path = Path()
        path.addCircle(
            (targetWidth.toFloat() - 1) / 2,
            (targetHeight.toFloat() - 1) / 2,
            Math.min(
                targetWidth.toFloat(),
                targetHeight.toFloat()
            ) / 2,
            Path.Direction.CCW
        )
        canvas.clipPath(path)
        canvas.drawBitmap(
            scaleBitmapImage,
            Rect(
                0, 0, scaleBitmapImage.width,
                scaleBitmapImage.height
            ),
            Rect(
                0, 0, targetWidth,
                targetHeight
            ), null
        )
        return targetBitmap
    }
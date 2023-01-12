package com.example.enliven.ui

import android.app.Activity
import android.content.Intent
import android.graphics.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <A: Activity>Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also{
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(it)
        finishAndRemoveTask()
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

private fun interpolate(a: Float, b: Float, proportion: Float): Float {
    return a + (b - a) * proportion
}

fun interpolateColor(a: Int, b: Int, proportion: Float): Int {
    require(!(proportion > 1 || proportion < 0)) { "proportion must be [0 - 1]" }
    val hsva = FloatArray(3)
    val hsvb = FloatArray(3)
    val hsv_output = FloatArray(3)
    Color.colorToHSV(a, hsva)
    Color.colorToHSV(b, hsvb)
    for (i in 0..2) {
        hsv_output[i] = interpolate(hsva[i], hsvb[i], proportion)
    }
    val alpha_a = Color.alpha(a)
    val alpha_b = Color.alpha(b)
    val alpha_output = interpolate(alpha_a.toFloat(), alpha_b.toFloat(), proportion)
    return Color.HSVToColor(alpha_output.toInt(), hsv_output)
}

fun manipulateColor(color: Int, factor: Float): Int {
    val a = Color.alpha(color)
    val r = Math.round(Color.red(color) * factor)
    val g = Math.round(Color.green(color) * factor)
    val b = Math.round(Color.blue(color) * factor)
    return Color.argb(
        a,
        Math.min(r, 255),
        Math.min(g, 255),
        Math.min(b, 255)
    )
}
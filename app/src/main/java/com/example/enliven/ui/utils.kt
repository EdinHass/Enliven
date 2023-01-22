package com.example.enliven.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.fragment.app.Fragment
import com.example.enliven.R
import com.getstream.sdk.chat.utils.extensions.activity
import com.google.android.material.snackbar.Snackbar
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.PartyFactory
import nl.dionsegijn.konfetti.core.Position.*
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(it)
        finishAndRemoveTask()
    }
}

fun Fragment.snackbar(message: String, retry: (() -> Unit)? = null) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).also {
        it.setAction("Ok") {
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
    require(!(proportion > 1 || proportion < 0)) { "proporijca mora biti [0 - 1]" }
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

fun getcurrentDateAndTime(offsetDays: Int): String? {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, offsetDays)
    val c = calendar.time
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    return simpleDateFormat.format(c)
}

enum class addXP {
    SOUNDS, HABITS, EMOTION
}

fun addXP(XP: Int, context: Context, view: View, case: addXP) {
    val sharedPref = context.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE)
    var currentXP = sharedPref.getInt("XP", 0)
    val snackbar = CustomSnack.make(context, view.rootView, "+${XP}XP")


    when (case) {
        addXP.SOUNDS -> {
            val lastSoundXP = sharedPref.getLong("lastSoundXP", 0)
            if (Calendar.getInstance().timeInMillis - lastSoundXP > 3000) {
                currentXP += XP
                sharedPref.edit().putInt("XP", currentXP).apply()
                sharedPref.edit().putLong("lastSoundXP", Calendar.getInstance().timeInMillis)
                    .apply()
                snackbar.show()
            }
        }
        addXP.HABITS -> {
            val lastHabitsXP = sharedPref.getLong("lastHabitsXP", 0)
            if (Calendar.getInstance().timeInMillis - lastHabitsXP > 300000) {
                currentXP += XP
                sharedPref.edit().putInt("XP", currentXP).apply()
                sharedPref.edit().putLong("lastHabitsXP", Calendar.getInstance().timeInMillis)
                    .apply()
                snackbar.show()
            }
        }
        addXP.EMOTION -> {
            val lastEmotionXP = sharedPref.getLong("lastEmotionXP", 0)
            if (Calendar.getInstance().timeInMillis - lastEmotionXP > 300000) {
                currentXP += XP
                sharedPref.edit().putInt("XP", currentXP).apply()
                sharedPref.edit().putLong("lastEmotionXP", Calendar.getInstance().timeInMillis)
                    .apply()
                snackbar.show()
            }
        }
    }
    if ((currentXP - XP) / 100 < currentXP / 100) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context, R.style.MyDialogTheme)
        builder.setView(R.layout.levelup_dialog)

        builder.setCancelable(true)
        builder.setPositiveButton("OK") { dialog, which -> }
        val dialog: AlertDialog = builder.create()
        dialog.show()
        val konfettiView: KonfettiView = dialog.findViewById<KonfettiView>(R.id.konfettiView)!!

        val emitterConfig = Emitter(3, TimeUnit.SECONDS).perSecond(100)
        konfettiView.start(
            PartyFactory(emitterConfig)
                .angle(Angle.BOTTOM)
                .spread(Spread.ROUND)
                .shapes(Arrays.asList(Shape.Square, Shape.Circle))
                .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                .setSpeedBetween(0f, 15f)
                .position(Relative(0.0, 0.0).between(Relative(1.0, 0.0)))
                .build()
        )
        dialog.findViewById<TextView>(R.id.lvlTextDialog)?.setText("LVL${currentXP / 100}")

    }
}

class CustomSnack {
    companion object {
        fun make(context: Context, RootView: View, Text: String): Snackbar {
            val snackbar = Snackbar.make(
                RootView,
                Text,
                Snackbar.LENGTH_SHORT
            )
                .setBackgroundTint(Color.parseColor("#55130c20"))
                .setTextColor(Color.parseColor("#c61369"))
                .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)


            val snackText =
                snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            snackText.textAlignment = View.TEXT_ALIGNMENT_CENTER
            snackText.textSize = 30F
            val face = Typeface.createFromAsset(context.assets, "alata.ttf")
            snackText.typeface = face
            (snackText.layoutParams as LinearLayout.LayoutParams).apply {
                bottomMargin = 30
            }
            return snackbar
        }
    }
}



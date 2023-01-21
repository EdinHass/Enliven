package com.example.enliven.ui.chat

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.enliven.EventDecorator
import com.example.enliven.R
import com.example.enliven.data.UserExtra
import com.example.enliven.databinding.FragmentProfileBinding
import com.example.enliven.ui.getcurrentDateAndTime
import com.google.firebase.auth.FirebaseAuth
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.squareup.picasso.Picasso
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var prefs: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = activity?.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE)!!
        binding = FragmentProfileBinding.bind(view)
        activity?.closeOptionsMenu()
        (activity as AppCompatActivity).supportActionBar?.title = "My Profile"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.usernameText.text = ChatClient.instance().getCurrentUser()?.name
        binding.phoneText.text = ChatClient.instance().getCurrentUser()?.getExtraValue("phone", "")
        Picasso.get().load(ChatClient.instance().getCurrentUser()?.image).into(binding.imageemotion)
        setupLevels()

        binding.anonSwitch.isChecked = prefs.getBoolean("Anon", false)

        binding.anonSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.usernameText.text = "Anonimno"
                binding.phoneText.text = "Anoniman Broj"

                ChatClient.instance().getCurrentUser()?.name = "Anonimno"
                ChatClient.instance().getCurrentUser()?.extraData?.set(UserExtra.PHONE, "Anoniman Broj")
                ChatClient.instance().updateUser(ChatClient.instance().getCurrentUser()!!).enqueue(){
                    if(it.isError){
                        Log.e("ERROR", it.error().message!!)
                    }
                }

                prefs.edit().putBoolean("Anon", true).apply()
            }else{
                ChatClient.instance().getCurrentUser()?.name = prefs.getString("loginName", null)!!
                ChatClient.instance().getCurrentUser()?.extraData?.set(UserExtra.PHONE, prefs.getString("loginPhone", null)!!)
                ChatClient.instance().updateUser(ChatClient.instance().getCurrentUser()!!).enqueue(){
                    if(it.isError){
                        Log.e("ERROR", it.error().message!!)
                    }
                }
                binding.usernameText.text = prefs.getString("loginName", null)
                binding.phoneText.text = prefs.getString("loginPhone", null)

                prefs.edit().putBoolean("Anon", false).apply()
            }

        }
    }


    private fun setupLevels() {
        val progressBar: ProgressBar = binding.lvlBar
        progressBar.max = 100
        progressBar.min = 0
        val levelText: TextView = binding.lvlText
        val currentXPText: TextView = binding.textCurrentXP
        val streaksText: TextView = binding.streakText
        streaksText.setText(prefs.getInt("currentStreak", 1).toString())
        val currentXP: Int = prefs.getInt("XP", 0)
        levelText.text = (currentXP / 100).toString()
        progressBar.setProgress(currentXP % 100, false)
        currentXPText.setText("${currentXP % 100}/100 XP")
        val calendarView: MaterialCalendarView =
            binding.streaksCalender
        val anyChartView: AnyChartView = binding.lvlChart
        val cartesian = AnyChart.line()
        cartesian.animation(true)
        val seriesData: MutableList<DataEntry> = ArrayList()
        cartesian.yAxis(0).title("Trenutni XP")
        seriesData.add(
            ValueDataEntry(
                "1",
                prefs.getInt(
                    getcurrentDateAndTime(-4),
                    prefs.getInt(
                        getcurrentDateAndTime(-3),
                        prefs.getInt(
                            getcurrentDateAndTime(-2),
                            prefs.getInt(getcurrentDateAndTime(-1), currentXP)
                        )
                    )
                )
            )
        )
        seriesData.add(
            ValueDataEntry(
                "2",
                prefs.getInt(
                    getcurrentDateAndTime(-3),
                    prefs.getInt(
                        getcurrentDateAndTime(-2),
                        prefs.getInt(getcurrentDateAndTime(-1), currentXP)
                    )
                )
            )
        )
        seriesData.add(
            ValueDataEntry(
                "3",
                prefs.getInt(
                    getcurrentDateAndTime(-2),
                    prefs.getInt(getcurrentDateAndTime(-1), currentXP)
                )
            )
        )
        seriesData.add(ValueDataEntry("4", prefs.getInt(getcurrentDateAndTime(-1), currentXP)))
        seriesData.add(ValueDataEntry("5", currentXP))
        val series1 = cartesian.line(seriesData)
        series1.color("#c61369")
        cartesian.legend().enabled(false)
        cartesian.legend().fontSize(8.0)
        cartesian.background().fill("#00000000")
        anyChartView.setChart(cartesian)
        anyChartView.setBackgroundColor("#00000000")
        calendarView.setBackgroundColor(Color.TRANSPARENT)
        calendarView.topbarVisible = false
        calendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE
        calendarView.state().edit()
            .setCalendarDisplayMode(CalendarMode.WEEKS)
            .commit()
        val set: MutableSet<CalendarDay> = HashSet()
        val currentLoginDates: Set<String?> =
            HashSet<String>(prefs.getStringSet("LoginDates", HashSet<String>()))
        for (i in -7..7) {
            if (currentLoginDates.contains(getcurrentDateAndTime(i))) {
                val curr = getcurrentDateAndTime(i)!!.split("-".toRegex()).toTypedArray()
                val day = CalendarDay.from(curr[0].toInt(), curr[1].toInt(), curr[2].toInt())
                set.add(day)
            }
        }
        calendarView.addDecorator(
            EventDecorator(
                resources.getColor(
                    R.color.Pallete3,
                    requireContext().theme
                ), set
            )
        )
    }
}


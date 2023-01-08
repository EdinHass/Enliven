package com.example.enliven.ui.auth

import android.content.Context
import com.example.enliven.R

data class Country(
    val phoneCode: Int,
    val countryCode: String
){
    override fun toString() = "$countryCode (+$phoneCode)"
}

fun Context.getCountries(): List<Country>{
    val listCountries = mutableListOf<Country>()

    resources.getStringArray(R.array.countries).forEach {
        val temp = it.split(",")
        val country = Country(temp[0].toInt(), temp[1])
        listCountries.add(country)
    }

    return listCountries
}
package com.example.weatherapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainHourly(
    val id: Long,
    val dt: Long,
    val temp: Double,
    val weather: DomainWeather
) : Parcelable
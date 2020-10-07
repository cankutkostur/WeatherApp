package com.example.weatherapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainDaily(
    val id: Long,
    val cityId: Long,
    val dt: Long,
    val temp: Temp,
    val weather: DomainWeather
) : Parcelable

@Parcelize
data class Temp(
    val day: Double,
    val night: Double,
) : Parcelable
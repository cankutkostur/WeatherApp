package com.example.weatherapp.domain

import android.os.Parcelable
import com.example.weatherapp.database.models.DatabaseWeather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainWeather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
) : Parcelable

fun DomainWeather.asDatabaseModel(): DatabaseWeather{
    return DatabaseWeather(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}
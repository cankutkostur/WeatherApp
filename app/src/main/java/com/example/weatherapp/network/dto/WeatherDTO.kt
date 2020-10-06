package com.example.weatherapp.network.dto

import com.example.weatherapp.database.models.DatabaseWeather
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

fun WeatherDTO.asDatabaseModel(): DatabaseWeather{
    return DatabaseWeather(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}
package com.example.weatherapp.network.dto

import com.squareup.moshi.Json

data class CurrentDTO(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "wind_deg")
    val windDeg: Int,
    val weather: List<WeatherDTO>
)
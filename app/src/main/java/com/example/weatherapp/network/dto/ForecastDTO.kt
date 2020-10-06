package com.example.weatherapp.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDTO(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @Json(name = "timezone_offset")
    val timezoneOffset: Int,
    val current: CurrentDTO,
    val hourly: List<HourlyDTO>,
    val daily: List<DailyDTO>
)
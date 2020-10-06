package com.example.weatherapp.network.dto

import com.example.weatherapp.database.models.Current
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
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

fun CurrentDTO.asDatabaseModel(): Current {
    return Current(
        dt = dt,
        sunrise = sunrise,
        sunset = sunset,
        temp = temp,
        feelsLike = feelsLike,
        pressure = pressure,
        humidity = humidity,
        dewPoint = dewPoint,
        uvi = uvi,
        clouds = clouds,
        visibility = visibility,
        windSpeed = windSpeed,
        windDeg = windDeg,
        weather = weather.first().asDatabaseModel()
    )
}
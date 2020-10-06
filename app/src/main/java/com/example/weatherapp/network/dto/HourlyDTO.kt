package com.example.weatherapp.network.dto

import com.example.weatherapp.database.models.DatabaseHourly
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyDTO(
    val dt: Long,
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val clouds: Int,
    val visibility: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "wind_deg")
    val windDeg: Int,
    val pop: Double,
    val weather: List<WeatherDTO>
)


fun List<HourlyDTO>.asDatabaseModel(cityId: Long): Array<DatabaseHourly> {
    return map {
        DatabaseHourly(
            dt = it.dt,
            cityId = cityId,
            temp = it.temp,
            weather = it.weather.first().asDatabaseModel()
        )
    }.toTypedArray()
}
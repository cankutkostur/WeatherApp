package com.example.weatherapp.network.dto

import com.example.weatherapp.database.models.DatabaseDaily
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DailyDTO(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TempDTO,
    @Json(name = "feels_like")
    val feelsLike: FeelsLikeDTO,
    val pressure: Int,
    val humidity: Int,
    @Json(name = "dew_point")
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "wind_deg")
    val windDeg: Int,
    val weather: List<WeatherDTO>
)

fun List<DailyDTO>.asDatabaseModel(cityId: Long): Array<DatabaseDaily> {
    return map {
        DatabaseDaily(
            dt = it.dt,
            cityId = cityId,
            temp = it.temp.asDatabaseModel(),
            weather = it.weather.first().asDatabaseModel()
        )
    }.toTypedArray()
}
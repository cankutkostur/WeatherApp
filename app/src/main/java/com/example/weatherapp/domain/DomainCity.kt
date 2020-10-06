package com.example.weatherapp.domain

import android.os.Parcelable
import com.example.weatherapp.database.models.Coords
import com.example.weatherapp.database.models.DatabaseCity
import com.example.weatherapp.database.models.asDomainModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainCity(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coords,
    val timezone: String,
    val timezoneOffset: Int,
    val current: Current
) : Parcelable

@Parcelize
data class Current(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feelsLike: Double,
    val pressure: Int,
    val humidity: Int,
    val dewPoint: Double,
    val uvi: Double,
    val clouds: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDeg: Int,
    val weather: DomainWeather
) : Parcelable

fun Current.asDatabaseModel(): com.example.weatherapp.database.models.Current{
    return com.example.weatherapp.database.models.Current(
        dt = dt,
        sunrise = sunrise,
        sunset = sunset,
        temp = temp,
        feelsLike = feelsLike,
        pressure = pressure,
        humidity = humidity,
        dewPoint =  dewPoint,
        uvi = uvi,
        clouds = clouds,
        visibility = visibility,
        windSpeed = windSpeed,
        windDeg = windDeg,
        weather = weather.asDatabaseModel()
    )
}

fun DomainCity.asDatabaseModel(): DatabaseCity {
    return DatabaseCity(
        id = id,
        name = name,
        country = country,
        coord = coord,
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        current = current.asDatabaseModel()
    )
}

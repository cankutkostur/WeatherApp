package com.example.weatherapp.database.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.DomainCity
import kotlinx.android.parcel.Parcelize

@Entity
data class DatabaseCity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val country: String,
    @Embedded
    val coord: Coords,
    val timezone: String?,
    val timezoneOffset: Int?,
    @Embedded
    val current: Current?
)

@Parcelize
data class Coords(
    val lon: Double,
    val lat: Double
) : Parcelable

data class Current(
    val dt: Long?,
    val sunrise: Long?,
    val sunset: Long?,
    val temp: Double?,
    val feelsLike: Double?,
    val pressure: Int?,
    val humidity: Int?,
    val dewPoint: Double?,
    val uvi: Double?,
    val clouds: Int?,
    val visibility: Int?,
    val windSpeed: Double?,
    val windDeg: Int?,
    @Embedded(prefix = "weather_")
    val weather: DatabaseWeather?
)

fun Current.asDomainModel(): com.example.weatherapp.domain.Current{
    return com.example.weatherapp.domain.Current(
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
        weather = weather?.asDomainModel()
    )
}

fun List<DatabaseCity>.asDomainModel(): List<DomainCity>{
    return map {
        it.asDomainModel()
    }
}

fun DatabaseCity.asDomainModel(): DomainCity{
    return DomainCity(
        id = id,
        name = name,
        country = country,
        coord = coord,
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        current = current?.asDomainModel()
    )
}
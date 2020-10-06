package com.example.weatherapp.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.DomainDaily

@Entity(foreignKeys = [ForeignKey(entity = DatabaseCity::class,
    parentColumns = ["id"],
    childColumns = ["cityId"],
    onDelete = ForeignKey.CASCADE)]
)
data class DatabaseDaily(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cityId: Long,
    val dt: Long,
    @Embedded
    val temp: Temp,
    @Embedded(prefix = "weather_")
    val weather: DatabaseWeather
)

data class Temp(
    val day: Double,
    val night: Double,
)

fun Temp.asDomainModel(): com.example.weatherapp.domain.Temp {
    return com.example.weatherapp.domain.Temp(
        day = day,
        night = night
    )
}

fun DatabaseDaily.asDomainModel(): DomainDaily {
    return DomainDaily(
        id = id,
        cityId = cityId,
        dt = dt,
        temp = temp.asDomainModel(),
        weather = weather.asDomainModel()
    )
}

fun List<DatabaseDaily>.asDomainModel(): List<DomainDaily> {
    return map {
        it.asDomainModel()
    }
}
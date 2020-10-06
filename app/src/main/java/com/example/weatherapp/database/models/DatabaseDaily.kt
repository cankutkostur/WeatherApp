package com.example.weatherapp.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
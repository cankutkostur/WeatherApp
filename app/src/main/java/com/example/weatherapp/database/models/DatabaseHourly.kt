package com.example.weatherapp.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.DomainHourly

@Entity(foreignKeys = [ForeignKey(entity = DatabaseCity::class,
    parentColumns = ["id"],
    childColumns = ["cityId"],
    onDelete = ForeignKey.CASCADE)]
)
data class DatabaseHourly(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cityId: Long,
    val dt: Long,
    val temp: Double,
    @Embedded(prefix = "weather_")
    val weather: DatabaseWeather
)

fun DatabaseHourly.asDomainModel(): DomainHourly{
    return DomainHourly(
        id = id,
        dt = dt,
        temp = temp,
        weather = weather.asDomainModel()
    )
}

fun List<DatabaseHourly>.asDomainModel(): List<DomainHourly> {
    return map{
        it.asDomainModel()
    }
}
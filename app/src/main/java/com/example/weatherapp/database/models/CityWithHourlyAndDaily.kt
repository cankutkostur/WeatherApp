package com.example.weatherapp.database.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily

data class CityWithHourlyAndDaily(
    @Embedded
    val city: DatabaseCity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val hourly: List<DatabaseHourly>,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val daily: List<DatabaseDaily>
)

fun List<CityWithHourlyAndDaily>.asDomainModel(): List<DomainCityWithHourlyAndDaily> {
    return map{
        DomainCityWithHourlyAndDaily(
            city = it.city.asDomainModel(),
            hourly = it.hourly.asDomainModel(),
            daily = it.daily.asDomainModel(),
        )
    }
}
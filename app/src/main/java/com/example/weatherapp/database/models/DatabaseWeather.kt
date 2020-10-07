package com.example.weatherapp.database.models

import com.example.weatherapp.domain.DomainWeather

data class DatabaseWeather(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
)

fun DatabaseWeather.asDomainModel(): DomainWeather{
    return DomainWeather(
        id = id,
        main = main,
        description = description,
        icon = icon
    )
}
package com.example.weatherapp.domain


data class DomainDaily(
    val id: Long,
    val cityId: Long,
    val dt: Long,
    val temp: Temp,
    val weather: DomainWeather
)

data class Temp(
    val day: Double,
    val night: Double,
)
package com.example.weatherapp.domain


data class DomainHourly(
    val id: Long,
    val dt: Long,
    val temp: Double,
    val weather: DomainWeather
)
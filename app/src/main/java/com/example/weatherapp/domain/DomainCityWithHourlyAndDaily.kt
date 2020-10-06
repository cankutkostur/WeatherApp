package com.example.weatherapp.domain

data class DomainCityWithHourlyAndDaily(
    val city: DomainCity,
    val hourly: List<DomainHourly>,
    val daily: List<DomainDaily>
)
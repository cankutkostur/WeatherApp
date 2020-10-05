package com.example.weatherapp.network.dto

data class WeatherDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)
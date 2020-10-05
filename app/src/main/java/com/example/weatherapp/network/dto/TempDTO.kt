package com.example.weatherapp.network.dto

data class TempDTO(
    val morn: Double,
    val day: Double,
    val eve: Double,
    val night: Double,
    val min: Double,
    val max: Double
)
package com.example.weatherapp.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeelsLikeDTO(
    val morn: Double,
    val day: Double,
    val eve: Double,
    val night: Double
)
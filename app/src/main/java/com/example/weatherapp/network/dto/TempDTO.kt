package com.example.weatherapp.network.dto

import com.example.weatherapp.database.models.Temp
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TempDTO(
    val morn: Double,
    val day: Double,
    val eve: Double,
    val night: Double,
    val min: Double,
    val max: Double
)

fun TempDTO.asDatabaseModel(): Temp{
    return Temp(
        day = day,
        night = night
    )
}
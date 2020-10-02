package com.example.weatherapp.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.database.models.Coords
import com.example.weatherapp.database.models.DatabaseCity

class DomainCity(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coords,
    var isSelected: Boolean = false
)

fun DomainCity.asDatabaseModel(): DatabaseCity{
    return DatabaseCity(
        id,
        name,
        country,
        coord
    )
}

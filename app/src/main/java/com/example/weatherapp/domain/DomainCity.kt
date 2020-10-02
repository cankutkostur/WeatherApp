package com.example.weatherapp.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

class DomainCity(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coords
)

data class Coords(val lon: Double,
                  val lat: Double)
package com.example.weatherapp.database.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.domain.DomainCity
import kotlinx.android.parcel.Parcelize

@Entity
data class DatabaseCity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val country: String,
    @Embedded
    val coord: Coords
)

@Parcelize
data class Coords(
    val lon: Double,
    val lat: Double
) : Parcelable

fun List<DatabaseCity>.asDomainModel(): List<DomainCity>{
    return map {
        DomainCity(
            id = it.id,
            name = it.name,
            country = it.country,
            coord = it.coord
        )
    }
}
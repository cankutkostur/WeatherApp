package com.example.weatherapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainCityWithHourlyAndDaily(
    val city: DomainCity,
    val hourly: List<DomainHourly>,
    val daily: List<DomainDaily>
) : Parcelable

fun List<DomainCityWithHourlyAndDaily>.setSelected(selected: List<DomainCity>): List<DomainCityWithHourlyAndDaily>{
    return map {
        it.city.selected = selected.contains(it.city)
        it
    }
}
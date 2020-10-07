package com.example.weatherapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainCityWithHourlyAndDaily(
    val city: DomainCity,
    val hourly: List<DomainHourly>,
    val daily: List<DomainDaily>
) : Parcelable
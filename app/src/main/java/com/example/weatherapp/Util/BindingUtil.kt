package com.example.weatherapp.Util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.domain.DomainCity

@BindingAdapter("cityName")
fun TextView.setSleepDurationFormatted(item: DomainCity?) {
    item?.let {
        text = item.name
    }
}
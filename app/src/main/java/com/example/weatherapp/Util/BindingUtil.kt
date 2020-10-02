package com.example.weatherapp.Util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.domain.DomainCity

@BindingAdapter("setCityName")
fun TextView.setCityName(item: DomainCity?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("setCountryName")
fun TextView.setCountryName(item: DomainCity?) {
    item?.let {
        text = "(" + item.country + ")"
    }
}
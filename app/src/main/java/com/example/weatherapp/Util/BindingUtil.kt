package com.example.weatherapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setCityName")
fun TextView.setCityName(item: String) {
    item?.let {
        text = item
    }
}

@BindingAdapter("setCountryName")
fun TextView.setCountryName(item: String) {
    item?.let {
        text = "(" + item + ")"
    }
}

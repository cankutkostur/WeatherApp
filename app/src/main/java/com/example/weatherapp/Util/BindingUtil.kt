package com.example.weatherapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily

@BindingAdapter("cityName")
fun TextView.setCityName(item: String) {
    item?.let{
        text = item
    }
}

@BindingAdapter("countryName")
fun TextView.setCountryName(item: String?) {
    item?.let {
        text = resources.getString(R.string.display_country, item)
    }
}

@BindingAdapter("temperature")
fun TextView.setTemperature(item: DomainCityWithHourlyAndDaily?) {
    item?.let {
        item.city.current?.temp?.let {
            text = resources.getString(R.string.display_temperature, item.city.current.temp)
        }
    }
}

@BindingAdapter("weatherImage")
fun ImageView.setWeatherImage(item: DomainCityWithHourlyAndDaily?) {
    item?.city?.current?.weather?.icon?.let {
        setImageResource(when (it.dropLast(1)) {
            "01" -> R.drawable.ic_sunny
            "02" -> R.drawable.ic_sunny_intervals
            "03" -> R.drawable.ic_white_cloud
            "04" -> R.drawable.ic_black_low_cloud
            "09" -> R.drawable.ic_cloudy_with_heavy_rain
            "10" -> R.drawable.ic_heavy_rain_showers
            "11" -> R.drawable.ic_thunderstorms
            "13" -> R.drawable.ic_cloudy_with_heavy_snow
            else -> R.drawable.ic_mist
        })
    }
}
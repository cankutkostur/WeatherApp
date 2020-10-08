package com.example.weatherapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.weatherapp.R
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily
import com.example.weatherapp.domain.DomainWeather
import com.example.weatherapp.domain.Temp
import java.time.LocalTime
import java.util.*

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
fun TextView.setTemperature(item: Double) {
    item?.let {
        item?.let {
            text = resources.getString(R.string.display_temperature, it)
        }
    }
}

@BindingAdapter("weatherText")
fun TextView.setWeatherText(item: DomainCityWithHourlyAndDaily?) {
    item?.let {
        item.city.current?.weather?.description?.let {
            text = it
        }
    }
}

@BindingAdapter("weatherImage")
fun ImageView.setWeatherImage(item: DomainWeather?) {
    item?.icon?.let {
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

@BindingAdapter("day")
fun TextView.setDay(item: Long?) {
    item?.let {
        text = convertLongToDateString(item * 1000).substringBefore(' ')
    }
}

@BindingAdapter("hour")
fun TextView.setHour(item: Long?) {
    item?.let {
        text = convertLongToDateString(item * 1000, ).takeLast(5)
    }
}

@BindingAdapter("pressure")
fun TextView.setPressure(item: Int?) {
    item?.let {
        text = resources.getString(R.string.display_pressure, it)
    }
}

@BindingAdapter("percent")
fun TextView.setPercent(item: Int?) {
    item?.let {
        text = resources.getString(R.string.display_percent, it)
    }
}

@BindingAdapter("distance")
fun TextView.setDistance(item: Int?) {
    item?.let {
        text = resources.getString(R.string.display_distance, it)
    }
}

@BindingAdapter("speed")
fun TextView.setSpeed(item: Double?) {
    item?.let {
        text = resources.getString(R.string.display_speed, it)
    }
}

@BindingAdapter("degree")
fun TextView.setDegree(item: Int?) {
    item?.let {
        text = resources.getString(R.string.display_degree, it)
    }
}

@BindingAdapter("fullDate")
fun TextView.setFullDate(item: Long?) {
    item?.let {
        text = convertLongToDateString(item * 1000)
    }
}
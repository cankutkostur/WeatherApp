package com.example.weatherapp.network

import com.example.weatherapp.network.dto.ForecastDTO
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
enum class WeatherApiExclude(val value: String) {
    CURRENT("current"), MINUTELY("minutely"), HOURLY("hourly"), DAILY("daily"), ALERTS("alerts")
}
enum class WeatherApiUnits(val value: String) {
    STANDARD("standard"), METRIC("metric"), IMPERIAL("imperial")
}


/**
 * A retrofit service to fetch a forecast.
 */
interface WeatherApiService {
    @GET("onecall")
    fun getForecast(@Query("lat") lat: Double,
                    @Query("lon") lon: Double,
                    @Query("appid") appid: String,
                    @Query("exclude") exclude: List<String>,
                    @Query("units") units: String = "metric"): Deferred<ForecastDTO>
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access.
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val weatherApi = retrofit.create(WeatherApiService::class.java)
}
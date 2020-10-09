package com.example.weatherapp.util

import android.content.Context
import com.example.weatherapp.database.models.Coords
import com.example.weatherapp.domain.DomainCity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


private const val FILE = "city.list.min.json"


fun getAssetCities(context: Context): List<JsonCity>? {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    val listType = Types.newParameterizedType(List::class.java, JsonCity::class.java)
    val adapter: JsonAdapter<List<JsonCity>> = moshi.adapter(listType)

    val myjson = context.assets.open(FILE).bufferedReader().use { it.readText() }

    return adapter.fromJson(myjson)
}


@JsonClass(generateAdapter = true)
data class JsonCity(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coords
)

fun JsonCity.asDomainModel(): DomainCity{
    return DomainCity(
        id = id,
        name = name,
        country = country,
        coord = coord,
        current = null,
        timezone = null,
        timezoneOffset = null
    )
}

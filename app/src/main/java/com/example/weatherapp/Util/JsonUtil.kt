package com.example.weatherapp.Util

import android.content.Context
import com.example.weatherapp.domain.Coords
import com.example.weatherapp.domain.DomainCity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


private const val FILE = "city.list.min.json"


suspend fun getAssetCities(context: Context): JsonCityContainer {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    val listType = Types.newParameterizedType(List::class.java, JsonCity::class.java)
    val adapter: JsonAdapter<List<JsonCity>> = moshi.adapter(listType)

    val myjson = context.assets.open(FILE).bufferedReader().use { it.readText() }

    val cities = adapter.fromJson(myjson)

    return JsonCityContainer(cities!!)
}

@JsonClass(generateAdapter = true)
data class JsonCityContainer(val cities: List<JsonCity>)

@JsonClass(generateAdapter = true)
data class JsonCity(
    val id: Long,
    val name: String,
    val country: String,
    val coord: Coords
)


fun JsonCityContainer.asDomainModel(): List<DomainCity> {
    return cities.map {
        DomainCity(
            id = it.id,
            name = it.name,
            country = it.country,
            coord = it.coord
        )
    }
}
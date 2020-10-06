package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weatherapp.util.JsonCity
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.database.models.Coords
import com.example.weatherapp.database.models.DatabaseCity
import com.example.weatherapp.database.models.asDomainModel
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.asDatabaseModel
import com.example.weatherapp.network.Network
import com.example.weatherapp.network.WeatherApiExclude
import com.example.weatherapp.network.dto.ForecastDTO
import com.example.weatherapp.network.dto.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository (private val database: WeatherDatabase){

    private val cityDao = database.cityDao
    private val hourlyDao = database.hourlyDao
    private val dailyDao = database.dailyDao

    val cities: LiveData<List<DomainCity>> =
        Transformations.map(cityDao.getCities()) {
            it.asDomainModel()
        }

    suspend fun deleteCity(city: DomainCity){
        withContext(Dispatchers.IO) {
            cityDao.delete(city.asDatabaseModel())
        }
    }

    suspend fun addCity(city: JsonCity){
        withContext(Dispatchers.IO) {
            val forecast = getForecast(city.coord.lat, city.coord.lon)
            insertForecast(city.id, city.name, city.country, city.coord, forecast)
        }
    }

    suspend fun refreshCities() {
        withContext(Dispatchers.IO) {
            cities.value?.map {
                val forecast = getForecast(it.coord.lat, it.coord.lon)

                insertForecast(it.id, it.name, it.country, it.coord, forecast)

            }
        }
    }

    private suspend fun getForecast(lat: Double, lon: Double): ForecastDTO{
        val f =  Network.weatherApi.getForecast(lat,
            lon,
            "9c66536f2830e831b613d51fc345be88",
            listOf(WeatherApiExclude.ALERTS.value,
                WeatherApiExclude.MINUTELY.value)).await()
        return f
    }

    private suspend fun insertForecast(id: Long, name: String, country: String, coord: Coords, forecast: ForecastDTO) {
        // Update city
        val updatedCity = DatabaseCity(id, name, country, coord,
            forecast.timezone, forecast.timezoneOffset, forecast.current.asDatabaseModel())
        cityDao.insert(updatedCity)

        // Update hourly
        val updatedHourly = forecast.hourly.asDatabaseModel(id)
        hourlyDao.insertAll(*updatedHourly)

        // Update daily
        val updatedDaily = forecast.daily.asDatabaseModel(id)
        dailyDao.insertAll(*updatedDaily)
    }
}
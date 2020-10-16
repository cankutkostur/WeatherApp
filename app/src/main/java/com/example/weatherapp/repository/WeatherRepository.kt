package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weatherapp.util.JsonCity
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.database.models.Coords
import com.example.weatherapp.database.models.DatabaseCity
import com.example.weatherapp.database.models.asDomainModel
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily
import com.example.weatherapp.domain.asDatabaseModel
import com.example.weatherapp.network.Network
import com.example.weatherapp.network.WeatherApiExclude
import com.example.weatherapp.network.dto.ForecastDTO
import com.example.weatherapp.network.dto.asDatabaseModel
import com.example.weatherapp.util.getAssetCities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository (private val database: WeatherDatabase){

    private val cityDao = database.cityDao
    private val hourlyDao = database.hourlyDao
    private val dailyDao = database.dailyDao

    val cities: LiveData<List<DomainCityWithHourlyAndDaily>> =
        Transformations.map(cityDao.getCitiesWithHourlyAndDaily()) {
            it.asDomainModel()
        }

    suspend fun deleteCity(city: DomainCity){
        withContext(Dispatchers.IO) {
            cityDao.delete(city.asDatabaseModel())
        }
    }

    suspend fun deleteCities(cities: MutableList<DomainCity>){
        withContext(Dispatchers.IO){
            cityDao.delete(*cities.asDatabaseModel())
        }
    }

    suspend fun addCity(city: DomainCity){
        withContext(Dispatchers.IO) {
            cityDao.insert(city.asDatabaseModel())
            refreshCities()
        }
    }

    suspend fun refreshCities() {
        withContext(Dispatchers.IO) {
            val list = cityDao.getCities().asDomainModel()
            list.forEach{
                val forecast = getForecast(it.coord.lat, it.coord.lon)

                if (forecast != null) {
                    insertForecast(
                        it.id,
                        it.name,
                        it.country,
                        it.coord,
                        forecast
                    )
                }
            }
        }
    }

    private suspend fun getForecast(lat: Double, lon: Double): ForecastDTO?{
        return try {
            Network.weatherApi.getForecast(
                lat,
                lon,
                "9c66536f2830e831b613d51fc345be88",
                listOf(
                    WeatherApiExclude.ALERTS.value,
                    WeatherApiExclude.MINUTELY.value
                )
            ).await()
        }catch (e: Exception){
            null
        }
    }

    private suspend fun insertForecast(id: Long, name: String, country: String, coord: Coords, forecast: ForecastDTO?) {
        // Update city
        val updatedCity = DatabaseCity(id, name, country, coord,
            forecast?.timezone, forecast?.timezoneOffset, forecast?.current?.asDatabaseModel()
        )
        cityDao.insert(updatedCity)

        forecast?.let {
            // Update hourly
            val updatedHourly = it.hourly.asDatabaseModel(id)
            hourlyDao.insertAll(*updatedHourly)

            // Update daily
            val updatedDaily = it.daily.asDatabaseModel(id)
            dailyDao.insertAll(*updatedDaily)
        }
    }
}
package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.weatherapp.database.WeatherDatabase
import com.example.weatherapp.database.models.asDomainModel
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository (private val database: WeatherDatabase){

    val cities: LiveData<List<DomainCity>> =
        Transformations.map(database.weatherDao.getAllCities()) {
            it.asDomainModel()
        }

    suspend fun deleteCity(city: DomainCity){
        withContext(Dispatchers.IO) {
            database.weatherDao.delete(city.asDatabaseModel())
        }
    }
}
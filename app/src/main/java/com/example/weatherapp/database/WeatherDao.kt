package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.weatherapp.database.models.DatabaseCity

@Dao
interface WeatherDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: DatabaseCity)

    @Query("SELECT * FROM databasecity")
    fun getAllCities(): LiveData<List<DatabaseCity>>

    @Delete
    suspend fun delete(city: DatabaseCity)
}
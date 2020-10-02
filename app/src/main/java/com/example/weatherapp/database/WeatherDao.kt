package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.database.models.DatabaseCity

@Dao
interface WeatherDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: DatabaseCity)

    @Query("SELECT * FROM databasecity")
    fun getAllCities(): LiveData<List<DatabaseCity>>
}
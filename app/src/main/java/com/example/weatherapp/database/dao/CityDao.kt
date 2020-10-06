package com.example.weatherapp.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.weatherapp.database.models.DatabaseCity

@Dao
interface CityDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: DatabaseCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg city: DatabaseCity)

    @Query("SELECT * FROM databasecity")
    fun getAllCities(): LiveData<List<DatabaseCity>>

    @Delete
    suspend fun delete(city: DatabaseCity)
}
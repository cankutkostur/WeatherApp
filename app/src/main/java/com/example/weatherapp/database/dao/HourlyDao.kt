package com.example.weatherapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.weatherapp.database.models.DatabaseHourly


@Dao
interface HourlyDao{
    @Insert
    fun insertAll(vararg items: DatabaseHourly)
}
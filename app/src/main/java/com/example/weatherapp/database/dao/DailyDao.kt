package com.example.weatherapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.weatherapp.database.models.DatabaseDaily


@Dao
interface DailyDao{
    @Insert
    fun insertAll(vararg items: DatabaseDaily)
}
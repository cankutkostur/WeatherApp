package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.database.dao.CityDao
import com.example.weatherapp.database.dao.DailyDao
import com.example.weatherapp.database.dao.HourlyDao
import com.example.weatherapp.database.models.DatabaseCity
import com.example.weatherapp.database.models.DatabaseDaily
import com.example.weatherapp.database.models.DatabaseHourly

@Database(entities = [DatabaseCity::class, DatabaseDaily::class, DatabaseHourly::class], version = 8, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
    abstract val dailyDao: DailyDao
    abstract val hourlyDao: HourlyDao
}

@Volatile
private lateinit var INSTANCE: WeatherDatabase

fun getDatabase(context: Context): WeatherDatabase {
    synchronized(WeatherDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java,
                "weather_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
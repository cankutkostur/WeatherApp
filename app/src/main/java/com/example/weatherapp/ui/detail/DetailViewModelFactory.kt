package com.example.weatherapp.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily

class DetailViewModelFactory(
    private val city: DomainCityWithHourlyAndDaily,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(city, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
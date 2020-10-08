package com.example.weatherapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily
import com.example.weatherapp.domain.DomainHourly

class DetailViewModel(city: DomainCityWithHourlyAndDaily, app: Application) : AndroidViewModel(app) {

    private val _selectedCity = MutableLiveData<DomainCityWithHourlyAndDaily>()
    val selectedCity: LiveData<DomainCityWithHourlyAndDaily>
        get() = _selectedCity

    init {
        _selectedCity.value = city

    }
}
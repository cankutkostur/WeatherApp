package com.example.weatherapp.ui.list

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.database.models.CityWithHourlyAndDaily
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily
import com.example.weatherapp.domain.asDatabaseModel
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListViewModel(app: Application) : AndroidViewModel(app) {

    private val database = getDatabase(app)
    private val weatherRepository = WeatherRepository(database)

    private val _favCities = weatherRepository.cities
    val favCities: LiveData<List<DomainCityWithHourlyAndDaily>>
        get() = _favCities

    private val _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    private val _navigateToCity = MutableLiveData<DomainCityWithHourlyAndDaily>()
    val navigateToCity: LiveData<DomainCityWithHourlyAndDaily>
        get() = _navigateToCity

    private var selected = mutableListOf<DomainCity>()
    private var _selectedCities = MutableLiveData<List<DomainCity>>()
    val selectedCities: LiveData<List<DomainCity>>
        get() = _selectedCities

    val isSelecting = Transformations.map(_selectedCities) {
        it.isNotEmpty()
    }

    init {
        _navigateToAdd.value = false
        _selectedCities.value = selected
        viewModelScope.launch {
            weatherRepository.refreshCities()
        }
    }

    fun addCity() {
        _navigateToAdd.value = true
    }

    fun addCityCompleted() {
        selected.removeAll(selected)
        _selectedCities.value = selected
        _navigateToAdd.value = false
    }

    fun onSelect(item: DomainCityWithHourlyAndDaily): Boolean {
        if (selected.contains(item.city)){
            selected.remove(item.city)
        }
        else {
            selected.add(item.city)
        }
        _selectedCities.value = selected

        return true
    }

    fun onCityClicked(item: DomainCityWithHourlyAndDaily) {
        if (selected.isNotEmpty()){
            onSelect(item)
        }
        else{
            onCityNavigate(item)
        }
    }

    fun onCityNavigate(item: DomainCityWithHourlyAndDaily){
        _navigateToCity.value = item
    }

    fun onCityNavigated() {
        selected.removeAll(selected)
        _selectedCities.value = selected
        _navigateToCity.value = null
    }

    fun onDelete(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherRepository.deleteCities(selected)
            }
            selected.removeAll(selected)
            _selectedCities.value = selected
        }
    }
}

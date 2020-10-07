package com.example.weatherapp.ui.list

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.domain.DomainCity
import com.example.weatherapp.domain.DomainCityWithHourlyAndDaily
import com.example.weatherapp.repository.WeatherRepository
import kotlinx.coroutines.launch


class ListViewModel(app: Application) : AndroidViewModel(app) {

    private val database = getDatabase(app)
    private val weatherRepository = WeatherRepository(database)

    var _favCities = weatherRepository.cities
    val favCities: LiveData<List<DomainCityWithHourlyAndDaily>>
        get() = _favCities

    private var _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    private var _navigateToCity = MutableLiveData<DomainCityWithHourlyAndDaily>()
    val navigateToCity: LiveData<DomainCityWithHourlyAndDaily>
        get() = _navigateToCity

    //TODO selecting and deleting must be revised
    private var _selectedCities = MutableLiveData<MutableList<DomainCity>>()
    val selectedCities: LiveData<MutableList<DomainCity>>
        get() = _selectedCities

    val isSelecting = Transformations.map(selectedCities){
        it.isNotEmpty()
    }

    init {
        viewModelScope.launch {
            weatherRepository.refreshCities()
        }
        _navigateToAdd.value = false
    }

    fun addCity() {
        _navigateToAdd.value = true
    }

    fun addCityCompleted() {
        _navigateToAdd.value = false
    }

    fun onSelect(item: DomainCityWithHourlyAndDaily): Boolean {
        viewModelScope.launch {
            weatherRepository.deleteCity(item.city)
        }
        return true
    }

    fun onCityClicked(item: DomainCityWithHourlyAndDaily) {
        //TODO implement
    }

    fun onCityNavigate(item: DomainCityWithHourlyAndDaily){
        _navigateToCity.value = item
    }

    fun onCityNavigated() {
        _navigateToCity.value = null
    }
}

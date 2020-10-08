package com.example.weatherapp.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.util.JsonCity
import com.example.weatherapp.util.getAssetCities
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.util.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddViewModel(app: Application) : AndroidViewModel(app) {
    private val database = getDatabase(app)
    private val weatherRepository = WeatherRepository(database)

    private var _selectedCity = MutableLiveData<JsonCity>()
    val selectedCity
        get() = _selectedCity

    private var _allCities: List<JsonCity>? = null

    private var _cities = MutableLiveData<List<JsonCity>>()
    val cities: LiveData<List<JsonCity>>
        get() = _cities

    init {
        // read cities json file with io thread
        viewModelScope.launch {
            _allCities = withContext(Dispatchers.IO){
                val cities = getAssetCities(getApplication())
                withContext(Dispatchers.Default){
                    cities?.sortedBy { it.name }
                }
            }
        }
    }

    // @param queryName = query string for filtering including cities
    // takes 10 of matching cities
    fun showCities(queryName: String) {
        _cities.value = _allCities?.filter { city ->
            city.name.startsWith(queryName, ignoreCase = true)
        }?.take(10)
    }

    fun onCitySelect(city: JsonCity){
        _selectedCity.value = city
    }

    fun onCitySelected(){
        viewModelScope.launch {
            _selectedCity.value?.let { weatherRepository.addCity(it.asDomainModel()) }
        }
        _selectedCity.value = null
    }
}
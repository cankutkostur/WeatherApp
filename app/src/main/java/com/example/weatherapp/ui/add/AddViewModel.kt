package com.example.weatherapp.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Util.asDomainModel
import com.example.weatherapp.Util.getAssetCities
import com.example.weatherapp.domain.DomainCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddViewModel(app: Application) : AndroidViewModel(app) {
    private var _selectedCity = MutableLiveData<DomainCity>()
    val selectedCity
        get() = _selectedCity

    private var allCities: List<DomainCity>? = null

    private var _cities = MutableLiveData<List<DomainCity>>()
    val cities: LiveData<List<DomainCity>>
        get() = _cities

    init {
        /*viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (dao.getCityCount() == 0) {
                    dao.insert(getAssetCities(getApplication()).asDatabaseModel())
                }
            }
        }*/

        // read cities json file with io thread
        viewModelScope.launch {
            allCities = withContext(Dispatchers.IO){
                val cities = getAssetCities(getApplication())
                withContext(Dispatchers.Default){
                    cities.asDomainModel().sortedBy { it.name }
                }
            }
        }
    }


    // @param queryName = query string for filtering including cities
    // takes 15 of matching cities
    fun showCities(queryName: String) {
        /*viewModelScope.launch {
            _cities.value = dao.getAllMatching(queryName)
        }*/
        _cities.value = allCities?.filter { city ->
            city.name.startsWith(queryName, ignoreCase = true)
        }?.take(15)
    }

    fun onCitySelect(city: DomainCity){
        _selectedCity.value = city
    }

    fun onCitySelected(){
        _selectedCity.value = null
    }
}
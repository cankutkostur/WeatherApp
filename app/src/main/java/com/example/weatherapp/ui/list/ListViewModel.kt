package com.example.weatherapp.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.database.getDatabase
import com.example.weatherapp.domain.DomainCity


class ListViewModel(app: Application) : AndroidViewModel(app) {
    val dao = getDatabase(app).weatherDao

    val favCities = dao.getAllCities()

    private var _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    //TODO change type int to city class
    private var _selectedCities = MutableLiveData<MutableList<Int>>()
    val selectedCities: LiveData<MutableList<Int>>
        get() = _selectedCities

    init {
        _navigateToAdd.value = false
        _selectedCities.value = mutableListOf<Int>()
    }

    fun addCity() {
        _navigateToAdd.value = true
    }

    fun addCityCompleted() {
        _navigateToAdd.value = false
    }

    fun onSelect(city: Int) {
        _selectedCities.value!!.add(city)
    }

    fun onCityClicked(city: DomainCity) {
        //TODO implement
    }

    fun onCityNavigated() {
        //TODO implement
    }
}

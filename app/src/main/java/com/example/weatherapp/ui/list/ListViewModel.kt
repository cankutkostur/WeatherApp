package com.example.weatherapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private var _navigateToAdd = MutableLiveData<Boolean>()
    val navigateToAdd: LiveData<Boolean>
        get() = _navigateToAdd

    init {
        _navigateToAdd.value = false
    }

    fun addCity(){
        _navigateToAdd.value = true
    }

    fun addCityCompleted(){
        _navigateToAdd.value = false
    }
}
package com.github.welshk.ukko.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.DataRepository
import com.github.welshk.ukko.data.models.WeatherDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for Details screen
 * Fetch weather data on initialization and post successful responses
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DataRepository) :
    ViewModel() {

    val weatherDetails: LiveData<WeatherDetails>
        get() = _weatherDetails
    private val _weatherDetails = MutableLiveData<WeatherDetails>()

    init {
        fetchWeatherDetails()
    }

    //Get the days data in detail
    private fun fetchWeatherDetails() {
        viewModelScope.launch {
            val response = repository.getWeatherDetails()
            if (response.isSuccessful) {
                val details = response.body()
                _weatherDetails.postValue(details)
            } else {
                //Handle error UI stuff here
            }
        }
    }
}
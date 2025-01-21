package com.github.welshk.ukko.app

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.DataRepository
import com.github.welshk.ukko.data.models.WeatherDetails
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch

/**
 * ViewModel for Details screen
 * Fetch weather data on initialization and post successful responses
 */
class DetailsViewModel(private val repository: DataRepository) : ViewModel() {

    val weatherDetails: LiveData<WeatherDetails>
        get() = _weatherDetails
    private val _weatherDetails = MutableLiveData<WeatherDetails>()

    //Get the days data in detail
    fun fetchWeatherDetails(location: Location) {
        viewModelScope.launch {
            val response = repository.getWeatherDetails(location)
            if (response.status == HttpStatusCode.OK) {
                val details = response.body<WeatherDetails>()
                details.let {
                    _weatherDetails.postValue(it)
                }
            } else {
                //Handle error UI stuff here
            }
        }
    }
}
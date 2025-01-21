package com.github.welshk.ukko.app

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Holds the location data that we can share amoung the Fragments
 */
class MainViewModel : ViewModel() {
    val userLocation: LiveData<Location>
        get() = _userLocation
    private val _userLocation = MutableLiveData<Location>()

    fun getLocation(): Location? {
        return _userLocation.value
    }

    fun setLocation(location: Location) {
        _userLocation.postValue(location)
    }
}
package com.github.welshk.ukko.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.LocationRepository
import com.github.welshk.ukko.data.WeatherRepository
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.utils.FormatUtil
import com.github.welshk.ukko.utils.HeroImageUtil
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * ViewModel for Dashboard screen
 * Fetch weather data on initialization and post successful responses
 */
class DashboardViewModel(
    private val context: Context,
    private val weatherRepo: WeatherRepository,
    private val locationRepo: LocationRepository
) : ViewModel() {
    private val weatherFlow = weatherRepo.weather
    private val locationFlow = locationRepo.userLocation

    init {
        viewModelScope.launch {
            locationRepo.userLocation.collect { location ->
                location?.let {
                    weatherRepo.fetchWeather(location)
                }
            }
        }
    }

    val uiState = combine(
        weatherFlow,
        locationFlow
    ) { weather, location ->
        UiState.Success(
            heroImage = HeroImageUtil.getHeroImage(weather),
            city = FormatUtil.formatCity(weather),
            country = FormatUtil.formatCountry(weather),
            time = FormatUtil.formatTime(weather),
            description = FormatUtil.formatDescription(weather),
            icon = "",
            tempLow = FormatUtil.formatTempLow(context, weather),
            tempHigh = FormatUtil.formatTempHigh(context, weather),
            temp = FormatUtil.formatTemp(context, weather),
            author = HeroImageUtil.getHeroImage(weather)?.author.toString(),
            site = HeroImageUtil.getHeroImage(weather)?.site.toString()
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    sealed interface UiState {
        data object Loading : UiState
        data object Error : UiState
        data class Success(
            val heroImage: HeroImage?,
            val city: String,
            val country: String,
            val time: String,
            val description: String,
            val icon: String,
            val tempLow: String,
            val tempHigh: String,
            val temp: String,
            val author: String,
            val site: String
        ) : UiState
    }
}
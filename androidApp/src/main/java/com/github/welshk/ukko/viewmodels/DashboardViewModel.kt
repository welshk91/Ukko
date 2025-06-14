package com.github.welshk.ukko.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.LocationRepository
import com.github.welshk.ukko.data.WeatherRepository
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.utils.FormatUtil
import com.github.welshk.ukko.utils.HeroImageUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for Dashboard screen
 * Fetch weather data on initialization and post successful responses
 */
class DashboardViewModel(
    private val weatherRepo: WeatherRepository,
    private val locationRepo: LocationRepository,
    private val formatUtil: FormatUtil
) : ViewModel() {
    private val weatherFlow = weatherRepo.weather
    private val locationFlow = locationRepo.userLocation

    private val _shouldShowForecast = MutableStateFlow(false)
    private val shouldShowForecastFlow: StateFlow<Boolean> = _shouldShowForecast.asStateFlow()

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
        locationFlow,
        shouldShowForecastFlow
    ) { weather, location, shouldShowForecast ->
        UiState.Success(
            heroImage = HeroImageUtil.getHeroImage(weather),
            city = formatUtil.formatCity(weather),
            country = formatUtil.formatCountry(weather),
            time = formatUtil.formatTime(weather),
            description = formatUtil.formatDescription(weather),
            icon = "",
            tempLow = formatUtil.formatTempLow(weather),
            tempHigh = formatUtil.formatTempHigh(weather),
            temp = formatUtil.formatTemp(weather),
            author = HeroImageUtil.getHeroImage(weather)?.author.toString(),
            site = HeroImageUtil.getHeroImage(weather)?.site.toString(),
            shouldShowForecast = shouldShowForecast
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    fun onForecastClicked() {
        _shouldShowForecast.value = !_shouldShowForecast.value
    }

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
            val site: String,
            val shouldShowForecast: Boolean
        ) : UiState
    }
}
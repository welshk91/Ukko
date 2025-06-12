package com.github.welshk.ukko.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.ForecastRepository
import com.github.welshk.ukko.data.LocationRepository
import com.github.welshk.ukko.data.models.openweathermap.forecast.Forecast
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/**
 * ViewModel for Slideout screen
 * Fetch forecast data on initialization and post successful responses
 */
class SlideoutViewModel(
    private val context: Context,
    private val forecastRepo: ForecastRepository,
    private val locationRepo: LocationRepository
) : ViewModel() {
    private val hasPermissionFlow = locationRepo.permissionStatus
    private val forecastFlow = forecastRepo.forecast
    private val locationFlow = locationRepo.userLocation

    init {
        viewModelScope.launch {
            locationRepo.userLocation.collect { location ->
                location?.let {
                    forecastRepo.fetchForecast(location)
                }
            }
        }
    }

    val uiState = combine(
        hasPermissionFlow,
        forecastFlow,
        locationFlow
    ) { hasPermission, forecast, location ->
        if (forecast != null) {
            UiState.Success(
                daysForecast = forecast
            )
        } else {
            UiState.Error
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    sealed interface UiState {
        data object Loading : UiState
        data object Error : UiState
        data class Success(
            val daysForecast: Forecast
        ) : UiState
    }
}
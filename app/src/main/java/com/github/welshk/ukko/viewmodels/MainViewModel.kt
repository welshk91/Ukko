package com.github.welshk.ukko.viewmodels

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.welshk.ukko.data.LocationPermission
import com.github.welshk.ukko.data.LocationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/**
 * Holds the location data that we can share amoung screens
 */
class MainViewModel(
    private val context: Context,
    private val locationRepo: LocationRepository
) : ViewModel() {
    private val hasPermissionFlow = locationRepo.permissionStatus
    private val locationFlow = locationRepo.userLocation

    val uiState = combine(
        hasPermissionFlow,
        locationFlow
    ) { hasPermission, location ->
        UiState.Success(
            permissionStatus = hasPermission
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    @Composable
    fun setPermissionRequest() {
        locationRepo.setPermissionRequest(context)
    }

    fun launchRequest() {
        locationRepo.launchPermissionRequest()
    }

    sealed interface UiState {
        data object Loading : UiState
        data object Error : UiState
        data class Success(
            val permissionStatus: LocationPermission
        ) : UiState
    }
}
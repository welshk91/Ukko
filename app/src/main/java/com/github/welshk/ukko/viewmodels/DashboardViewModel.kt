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
 * ViewModel for Dashboard, our first screen
 * Not much on this screen other than handling moving to details screen
 */
class DashboardViewModel(
    private val context: Context,
    private val locationRepo: LocationRepository
) : ViewModel() {
    private val locationFlow = locationRepo.userLocation
    private val hasPermissionFlow = locationRepo.permissionStatus

    val uiState = combine(
        locationFlow,
        hasPermissionFlow
    ) { location, hasPermission ->
        if (hasPermission == LocationPermission.FINE) {
            UiState.Success(
                buttonText = "Click for Location Permission",
                permissionText = "Have Permission? ${hasPermission}",
                locationText = "Location: ${location?.latitude}, ${location?.longitude}"
            )
        } else if (hasPermission == LocationPermission.COURSE) {
            UiState.Success(
                buttonText = "Click for Location Permission",
                permissionText = "Have Permission? ${hasPermission}",
                locationText = "Location: ${location?.latitude}, ${location?.longitude}"
            )
        } else {
            UiState.Success(
                buttonText = "Click for Location Permission",
                permissionText = "Have Permission? ${hasPermission}",
                locationText = "Location: ${location?.latitude}, ${location?.longitude}"
            )
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        UiState.Loading
    )

    @Composable
    fun setPermissionRequest() {
        locationRepo.setPermissionRequest(context)
    }

    fun onButtonClicked() {
        locationRepo.launchPermissionRequest()
    }

    sealed interface UiState {
        data object Loading : UiState
        data class Success(
            val buttonText: String,
            val permissionText: String,
            val locationText: String
        ) : UiState
    }
}
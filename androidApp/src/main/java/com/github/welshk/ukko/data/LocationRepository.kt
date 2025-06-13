package com.github.welshk.ukko.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repo for handling Location.
 * Source:
 * https://www.freecodecamp.org/news/requesting-location-permissions-in-jetpack-compose/
 */
class LocationRepository {
    private val _userLocation = MutableStateFlow<Location?>(null)
    val userLocation: StateFlow<Location?> = _userLocation.asStateFlow()

    val _permissionStatus = MutableStateFlow(LocationPermission.NONE)
    val permissionStatus = _permissionStatus.asStateFlow()

    val _showPermissionRationale = MutableStateFlow(false)
    val showPermissionRationale = _showPermissionRationale.asStateFlow()

    val _directUserToAppSettings = MutableStateFlow(false)
    val directUserToAppSettings = _directUserToAppSettings.asStateFlow()

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private lateinit var permissionRequest: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>

    fun launchPermissionRequest() {
        if (this::permissionRequest.isInitialized) {
            permissionRequest.launch(locationPermissions)
        }
    }

    fun getLastKnownLocation(
        context: Context
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Dont have permission; ask for it
            _showPermissionRationale.value = true
            return
        }

        LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener {
            _userLocation.value = it
        }
    }

    @Composable
    fun setPermissionRequest(
        context: Context,
        fineAccess: () -> Unit = {},
        coarseAccess: () -> Unit = {},
        noAccess: () -> Unit = {}
    ): ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>> {
        permissionRequest = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrElse(Manifest.permission.ACCESS_FINE_LOCATION) { false } -> {
                    _permissionStatus.value = LocationPermission.FINE
                    getLastKnownLocation(context)
                    fineAccess.invoke()
                }

                permissions.getOrElse(Manifest.permission.ACCESS_COARSE_LOCATION) { false } -> {
                    _permissionStatus.value = LocationPermission.COURSE
                    getLastKnownLocation(context)
                    coarseAccess.invoke()
                }

                else -> {
                    _permissionStatus.value = LocationPermission.NONE
                    noAccess.invoke()
                }
            }
        }
        return permissionRequest
    }
}
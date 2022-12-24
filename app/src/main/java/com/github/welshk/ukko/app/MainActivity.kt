package com.github.welshk.ukko.app

import android.Manifest
import android.location.Location
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.welshk.ukko.R
import com.github.welshk.ukko.utils.LocationUtil
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity.
 * Mainly makes sure we get proper Location info
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var permissionRequest: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        createPermissionRequest()
    }

    override fun onStart() {
        super.onStart()
        launchPermissionRequest()
    }

    private fun createPermissionRequest() {
        permissionRequest = LocationUtil.permissionRequest(this, {
            LocationUtil.getLastKnownLocation(this, fusedLocationClient) {
                locationSuccess(it)
            }
        }, {
            LocationUtil.getLastKnownLocation(this, fusedLocationClient) {
                locationSuccess(it)
            }
        }) {
            locationAccessDenied()
        }
    }

    private fun launchPermissionRequest() {
        permissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun locationSuccess(location: Location) {
        viewModel.setLocation(location)
    }

    private fun locationAccessDenied() {
        Snackbar.make(
            this,
            window.decorView.rootView,
            getString(R.string.snackbar_location_denied),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}
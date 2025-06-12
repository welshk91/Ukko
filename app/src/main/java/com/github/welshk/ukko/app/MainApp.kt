package com.github.welshk.ukko.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.welshk.ukko.data.LocationPermission
import com.github.welshk.ukko.screens.DashboardScreenRoute
import com.github.welshk.ukko.ui.HideSystemBars
import com.github.welshk.ukko.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainApp(
    viewModel: MainViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    when (uiState) {
        MainViewModel.UiState.Error -> Unit
        MainViewModel.UiState.Loading -> Unit
        is MainViewModel.UiState.Success -> {
            HideSystemBars()
            val permissionStatus = uiState.permissionStatus

            viewModel.setPermissionRequest()

            LifecycleStartEffect(key1 = permissionStatus) {
                if (permissionStatus == LocationPermission.NONE) {
                    viewModel.launchRequest()
                }
                onStopOrDispose { }
            }
        }
    }

    UkkoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DashboardScreenRoute()
        }
    }
}
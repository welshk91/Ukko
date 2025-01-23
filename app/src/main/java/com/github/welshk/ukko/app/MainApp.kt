package com.github.welshk.ukko.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.welshk.ukko.screens.DetailsScreenRoute
import com.github.welshk.ukko.viewmodels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainApp(
    viewModel: MainViewModel = koinViewModel()
) {
    UkkoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DetailsScreenRoute()
        }
    }
}